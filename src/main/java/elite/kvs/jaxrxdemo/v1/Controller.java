package elite.kvs.jaxrxdemo.v1;

import elite.kvs.jaxrxdemo.model.Course;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

@Component
@Path("/v1/courses")
public class Controller {
    @GET
    public Response getAllCourses() {
        return Response.accepted(new Course()).build();
    }
}
