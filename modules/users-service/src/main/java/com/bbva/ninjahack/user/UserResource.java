package com.bbva.ninjahack.user;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

@Path("/api/users")
@Produces(APPLICATION_JSON)
public class UserResource {

    private static final Logger LOGGER = Logger.getLogger(UserResource.class);

    @Inject
    UserService service;
    
    @GET
    @Produces(TEXT_PLAIN)
    @Path("/hello")
    public String hello() {
        return "hello";
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Authenticate the recived user")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Person.class, required = true)))
    public Response login(@Valid Person user) throws Exception {
        LOGGER.debug("Login User by email " + user.email);
        // Sleep para simular tiempo de autenticacion
        Thread.sleep(2000);
        Person result = service.login(user);
        LOGGER.debug("Result " + result);
        if (null != result) {
            return Response.ok(result).build();
        } else {
            return Response.status(401).build();
        }
    }
}