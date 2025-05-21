package org.acme;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class McpAgentClient {

    public static void main(String[] args) throws Exception {
        // Crear un mensaje tipo taskRequest
        McpMessage message = new McpMessage();
        message.type = "taskRequest";
        message.agentId = "agent-001";
        message.content = "más cobra";

        // Serializar a JSON
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(message);

        // Crear la petición HTTP POST
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/mcp")) // Cambia si usas otro puerto o dominio
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        // Enviar el mensaje al servidor
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Mostrar respuesta
        System.out.println("📤 Respuesta del servidor: " + response.body());
    }
}
