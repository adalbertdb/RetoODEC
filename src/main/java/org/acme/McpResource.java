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
        System.out.println("📝 Contenido original recibido: " + message.content);

        if ("taskRequest".equals(message.type)) {
            String respuesta = procesarTarea(message.content);
            return Response.ok(new McpResponse(respuesta)).build();
        }

        return Response.ok().build();
    }

    private String procesarTarea(String tarea) {
        String pregunta = tarea.toLowerCase().replaceAll("[^a-záéíóúñü\\s]", "").trim();
        System.out.println("📌 Pregunta normalizada: " + pregunta);
        System.out.println("🔍 Total de jugadores en la base de datos: " + jugadors.count());

        if (pregunta.contains("más cobra") || pregunta.contains("máximo sueldo") || pregunta.contains("quién cobra más")) {
            System.out.println("✅ Pregunta detectada: máximo sueldo");
            jugadors jugador = jugadors.find("WHERE equip = 'bar'"+"order by sou desc").firstResult();
            if (jugador != null) {
                System.out.println("✅ Jugador encontrado: " + jugador.nom + ", sueldo: " + jugador.sou);
                return "El jugador que más cobra es " + jugador.nom + " con un sueldo de " + jugador.sou;
            } else {
                System.out.println("❌ No se encontraron jugadores.");
                return "No hay datos de jugadores.";
            }
        } else if (pregunta.contains("menos cobra") || pregunta.contains("mínimo sueldo") || pregunta.contains("quién cobra menos")) {
            System.out.println("✅ Pregunta detectada: mínimo sueldo");
            jugadors jugador = jugadors.find("order by sou asc").firstResult();
            if (jugador != null) {
                System.out.println("✅ Jugador encontrado: " + jugador.nom + ", sueldo: " + jugador.sou);
                return "El jugador que menos cobra es " + jugador.nom + " con un sueldo de " + jugador.sou;
            } else {
                System.out.println("❌ No se encontraron jugadores.");
                return "No hay datos de jugadores.";
            }
        }

        System.out.println("❓ Pregunta no reconocida.");
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