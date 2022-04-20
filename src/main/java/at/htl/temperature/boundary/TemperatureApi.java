package at.htl.temperature.boundary;

import at.htl.temperature.control.TemperatureRepository;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Month;

@Path("api/temperature")
public class TemperatureApi {

    @Inject
    TemperatureRepository temperatureRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response
                .ok(temperatureRepository
                        .listAll()
                ).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance monthForm(
            @FormParam("month") String monthString
    ) {
        Month month = Month.valueOf(monthString);
        return TemperatureResource.Templates.temperature(
                temperatureRepository.findByMonth(month)
        );
    }
}
