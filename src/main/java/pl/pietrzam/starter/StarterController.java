package pl.pietrzam.starter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Component
@Path(StarterController.STARTER_URL)
@AllArgsConstructor
public class StarterController {

    static final String STARTER_URL = "/starter";

    private final StarterService starterService;

    @GET
    @Produces("application/json")
    public String hello() {
        return "Hello Starter!";
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String helloTo(@PathParam("id") final int id) {
        final String user = starterService.getUser(id);
        return String.format("Hello %s!", user);
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public String storeUser(@PathParam("id") final int id, final String user, @Context final HttpServletResponse response) {
        starterService.updateUser(id, user);
        response.setStatus(HttpServletResponse.SC_CREATED);

        return String.format("Location: %s/%d", STARTER_URL, id);
    }

    @POST
    @Consumes("application/json")
    public String storeUser(final String user, @Context final HttpServletResponse response) {
        final int userId = starterService.createUser(user);

        response.setStatus(HttpServletResponse.SC_CREATED);
        return String.format("Location: %s/%d", STARTER_URL, userId);
    }

}
