package org.acme.IA;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.concurrent.ExecutionException;

@Path("/ia")
public class IAResource {

    @Inject
    IAService iaService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String getRespuesta(RequestIA request) throws ExecutionException, InterruptedException {
        String prompt = request.getPrompt();
        return iaService.consultarIA(prompt);
    }
}
