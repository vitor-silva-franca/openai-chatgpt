package com.vitorsilvafranca.openai_chatgpt.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;

public class ChatGPTService {

    public void callGPT() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, """             
                {
                    "model": "gpt-4",
                    "messages": [
                        {
                            "role": "user",
                            "content": "Que dia é hoje?"
                        }
                    ]
                }""");
        Request request = new Request.Builder().url("https://api.openai.com/v1/chat/completions").method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization",
                        "Bearer API_KEY")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonResponse = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(jsonResponse);
                JsonNode contentNode = rootNode.path("choices").get(0).path("message").path("content");
                System.out.println("Content: " + contentNode.asText());
            } else {
                System.out.println("Error: " + response.message());
            }
        }
    }


}
