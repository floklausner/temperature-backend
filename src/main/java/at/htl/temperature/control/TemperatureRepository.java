package at.htl.temperature.control;

import at.htl.temperature.entity.Temperature;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.out;

@ApplicationScoped
public class TemperatureRepository implements PanacheRepository<Temperature> {

    @Inject
    Logger LOG;

    private String location;


    public List<Temperature> findByMonth(Month month) {
        return find("month", month).list();
    }

    /**
     * Verwenden sie
     * 1. einen stream mit lambda-expressions zum Einlesen
     * 2. rufen Sie in einer lambda-expression die Methode parseCsvLine(...) auf
     *
     * @param fileName
     */
    void readTemperatureFile(final String fileName) {
        // https://github.com/quarkusio/quarkus/issues/2746#issuecomment-506941113
        // https://github.com/quarkusio/quarkus/pull/2910#issuecomment-504671096
//        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
//        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
//        br.lines()
//                .skip(1)
//                .peek(out::println)
////                .forEach(this::parseCsvLine);
//                .forEach(out::println);

        //https://reflectoring.io/processing-files-using-java-8-streams/
        try {

            // Einlesen der Location
            Stream<String> lines = Files.lines(Paths.get(fileName));
            this.location = lines.skip(4).findFirst().get().substring(11);
            LOG.info(this.location);

            // Einlesen der Temperaturwerte
            lines = Files.lines(Paths.get(fileName));
            lines.skip(14)
                    .forEach(line -> parseCsvLine(line))
                    //.forEach(out::println)
                    ;
        } catch (IOException e) {
            //LOG.error(e.getMessage());
            e.printStackTrace();
        }

    }

    @Transactional
    List<Temperature> parseCsvLine(String line) {
        String[] elements = line.split(";");

        List<Temperature> temps = new LinkedList<>();
        int yyyy = Integer.parseInt(elements[0]);

        for (int i = 1; i < 13; i++) {
            if (!elements[i].equals("999999")) {
                final Temperature temperature =
                        new Temperature(
                                yyyy,
                                i,
                                Double.parseDouble(elements[i]),
                                this.location
                        );
                temps.add(temperature);
                LOG.info(temperature);
            }
        }
        persist(temps);
        return temps;
    }


}
