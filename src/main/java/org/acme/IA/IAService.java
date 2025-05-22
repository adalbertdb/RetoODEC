package com.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CompletionStage;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class IAService {

    @Inject
    @RestClient
    static OllamaClient ollamaClient;

    public static CompletionStage<Response> consultarIAAsync(String prompt) {
        Response response = ollamaClient.generarAsync(prompt).toCompletableFuture().get();
        String resultado = response.readEntity(String.class);
        response.close();
        return resultado;
    }

}
