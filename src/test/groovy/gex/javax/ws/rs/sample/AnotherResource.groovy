package gex.javax.ws.rs.sample

import javax.ws.rs.*
import javax.ws.rs.core.Response

import static javax.ws.rs.core.MediaType.APPLICATION_JSON

/**
 * Created by domix on 03/03/15.
 */
@Path("/user")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
class AnotherResource implements UserResource {

  @Override
  @GET
  public Response getUsers(Long from, Long size) {
    return null;
  }

  @Override
  //@POST
  public Response postUsers() {
    return null;
  }

  @Override
  Response putUser(@DefaultValue('iamEdu') @QueryParam('name') String name, Integer foo) {
    return null
  }
}
