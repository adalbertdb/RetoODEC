package com.ejemplo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class IAService {

    @Inject
    @RestClient
    OllamaClient ollamaClient;

    public String consultarIAAsync(String prompt) {
        try {
            Response response = ollamaClient.generarAsync(prompt).toCompletableFuture().get();
            String resultado = response.readEntity(String.class);
            response.close();
            return resultado;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

}
