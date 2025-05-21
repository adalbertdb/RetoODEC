package org.acme;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/mcp")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class McpResource {

    @POST
public Response receiveMessage(McpMessage message) {
    System.out.println("📥 MCP mensaje recibido: " + message.type + " desde " + message.agentId);

    if ("taskRequest".equals(message.type)) {
        String respuesta = procesarTarea(message.content); // Usamos content, que es la pregunta real
        return Response.ok(new McpResponse(respuesta)).build();
    }

    return Response.ok().build();
}



private String procesarTarea(String tarea) {
    String pregunta = tarea.toLowerCase();

    if (pregunta.contains("más cobra") || pregunta.contains("máximo sueldo") || pregunta.contains("quién cobra más")) {
        // Busca jugador con máximo sueldo
        jugadors jugador = jugadors.find("order by sou desc").firstResult();
        if (jugador != null) {
            return "El jugador que más cobra es " + jugador.nom + " con un sueldo de " + jugador.sou;
        } else {
            return "No hay datos de jugadores.";
        }
    } else if (pregunta.contains("menos cobra") || pregunta.contains("mínimo sueldo") || pregunta.contains("quién cobra menos")) {
        // Busca jugador con mínimo sueldo
        jugadors jugador = jugadors.find("order by sou asc").firstResult();
        if (jugador != null) {
            return "El jugador que menos cobra es " + jugador.nom + " con un sueldo de " + jugador.sou;
        } else {
            return "No hay datos de jugadores.";
        }
    }

    return "No entiendo la pregunta.";
}



    public static class McpResponse {
        public String answer;

        public McpResponse() {}

        public McpResponse(String answer) {
            this.answer = answer;
        }
    }
}
