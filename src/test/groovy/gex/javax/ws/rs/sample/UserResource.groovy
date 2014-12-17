package gex.javax.ws.rs.sample

import javax.ws.rs.*
import javax.ws.rs.core.Response

import static javax.ws.rs.core.MediaType.APPLICATION_JSON

/**
 * Created by domix on 12/16/14.
 */
@Path("/user")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
interface UserResource {

  @GET
  Response getUsers(@QueryParam("from") Long from, @QueryParam("size") Long size)

  @POST
  Response postUsers()

}