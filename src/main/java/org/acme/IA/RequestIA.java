package org.acme.IA;

public class RequestIA {
    public String model = "llama3";
    private String prompt;

    public RequestIA() {}

    public RequestIA(String prompt) {
        this.prompt = prompt;
    }

    // Puedes añadir más campos opcionales si los usas


    public String getPrompt() {
        return prompt;
    }

}
