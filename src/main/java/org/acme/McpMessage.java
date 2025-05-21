package org.acme;

public class McpMessage {
    public String type;
    public String agentId;
    public String content;

    public McpMessage() {}

    public McpMessage(String type, String agentId, String content) {
        this.type = type;
        this.agentId = agentId;
        this.content = content;
    }
}
