package at.htl.temperature.control;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class InitBean {

    @Inject
    TemperatureRepository temperatureRepository;

    void init(@Observes StartupEvent event) {
        temperatureRepository.readTemperatureFile("temperatures.csv");
    }

}
