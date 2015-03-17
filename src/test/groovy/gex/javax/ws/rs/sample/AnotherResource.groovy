/*
 *
 * Copyright (C) 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
