package org.acme.IA;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.concurrent.CompletionStage;


@Path("/api/generate")
@RegisterRestClient(configKey = "ollama-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)



public interface OllamaClient {
    @POST
    CompletionStage<Response> generarAsync(RequestIA request);
}
