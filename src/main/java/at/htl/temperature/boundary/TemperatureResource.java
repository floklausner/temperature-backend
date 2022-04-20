package at.htl.temperature.boundary;

import at.htl.temperature.control.TemperatureRepository;
import at.htl.temperature.entity.Temperature;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Month;
import java.util.List;

@Path("temperature")
public class TemperatureResource {

    @Inject
    TemperatureRepository temperatureRepository;

    private Month month = Month.JANUARY;

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance temperature(List<Temperature> temperatureList);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        //List<Double> temperatures = temperatureRepository.findAll().project()
        //return Templates.temperature(temperatureRepository.listAll());
        return Templates.temperature(temperatureRepository.findByMonth(getMonth()));
    }
}
