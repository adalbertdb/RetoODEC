package com.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import java.util.concurrent.CompletionStage;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/ia")
public class IAResource {

    @Inject
    IAService iaService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    IAService service = new IAService();
    CompletionStage<Response> response = service.consultarIAAsync("tu prompt");
}