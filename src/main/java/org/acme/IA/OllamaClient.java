package com.ejemplo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Map;

@Path("/api/generate")
@RegisterRestClient(configKey = "ollama-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface OllamaClient {
    @GET
    @Path("/generate")
    CompletionStage<Response> generarAsync(@QueryParam("prompt") String prompt);
}
