package org.acme.message;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.logging.Logger;
import java.io.InputStream;
import java.time.Instant;

@Path("/api/mcp")
public class MessageResource {
    private static final Logger LOG = Logger.getLogger(MessageResource.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @GET
    @Path("/message")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage() {
        try (InputStream is = getClass().getClassLoader()
                .getResourceAsStream("message.json")) {

            if (is == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(createError("Archivo message.json no encontrado"))
                        .build();
            }

            Message message = mapper.readValue(is, Message.class);
            return Response.ok(message).build();

        } catch (Exception e) {
            LOG.error("Error procesando mensaje", e);
            return Response.serverError()
                    .entity(createError(e.getMessage()))
                    .build();
        }
    }

    private Message createError(String errorMsg) {
        Message error = new Message();

        Header header = new Header();
        header.setId("urn:error:mcp:system");
        header.setTitle("Error del Sistema");
        header.setType("error");
        header.setMessageType("ERROR");
        header.setTimestamp(Instant.now().toString());

        ErrorPayload payload = new ErrorPayload();
        payload.setErrorType("SYSTEM_ERROR");
        payload.setErrorMessage(errorMsg);

        error.setHeader(header);
        error.setPayload(payload);

        return error;
    }
}