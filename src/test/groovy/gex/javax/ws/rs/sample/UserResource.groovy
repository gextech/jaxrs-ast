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