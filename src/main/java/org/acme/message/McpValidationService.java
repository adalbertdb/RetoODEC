package org.acme.message;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import java.io.InputStream;
import java.util.Set;

public class McpValidationService {
    private final JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
    private final ObjectMapper mapper = new ObjectMapper();

    public void validateMessage(Message message) throws ValidationException {
        try {
            JsonNode headerNode = mapper.valueToTree(message.getHeader());
            validateAgainstSchema(headerNode, "header.schema.json");

            JsonNode payloadNode = mapper.valueToTree(message.getPayload());
            String schemaFile = determineSchemaFile(message.getHeader().getType());
            validateAgainstSchema(payloadNode, schemaFile);
        } catch (Exception e) {
            throw new ValidationException("Error validating message", e);
        }
    }

    private void validateAgainstSchema(JsonNode node, String schemaFile) throws ValidationException {
        try (InputStream is = getClass().getClassLoader()
                .getResourceAsStream(schemaFile)) {

            if (is == null) {
                throw new ValidationException("Schema file not found: " + schemaFile);
            }

            // Usamos la implementación correcta de JsonSchema
            JsonSchema schema = schemaFactory.getSchema(is);
            Set<ValidationMessage> errors = schema.validate(node);

            if (!errors.isEmpty()) {
                throw new ValidationException("Validation failed: " + errors);
            }
        } catch (Exception e) {
            throw new ValidationException("Schema validation error", e);
        }
    }

    private String determineSchemaFile(String messageType) throws ValidationException {
        return switch (messageType) {
            case "TASK_REQUEST" -> "taskRequest.schema.json";
            case "CONTEXT_UPDATE" -> "contextUpdate.schema.json";
            case "COMPLETION" -> "completion.schema.json";
            default -> throw new ValidationException("Unknown message type: " + messageType);
        };
    }

    public static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
        public ValidationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}