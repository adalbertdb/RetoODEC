package org.acme.IA;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class IAService {

    @Inject
    @RestClient
    OllamaClient ollamaClient;

    public String consultarIA(String prompt) throws ExecutionException, InterruptedException {

        RequestIA request = new RequestIA(prompt);
        Response response = ollamaClient.generarAsync(request).toCompletableFuture().get();
        if (response.getStatus() == 200) {
            String resultado = response.readEntity(String.class);
            response.close();
            return resultado;
        } else {
            response.close();
            return "Error: Código de respuesta " + response.getStatus();
        }
    }


}
