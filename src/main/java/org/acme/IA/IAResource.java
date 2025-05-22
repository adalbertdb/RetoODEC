package com.ejemplo;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/ia")
public class IAResource {

    @Inject
    IAService iaService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRespuesta(@QueryParam("prompt") String prompt) {
        return IAService.consultarIAAsync(prompt);
    }
}
