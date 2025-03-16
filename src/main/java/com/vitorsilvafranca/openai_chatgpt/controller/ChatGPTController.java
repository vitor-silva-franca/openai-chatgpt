package com.vitorsilvafranca.openai_chatgpt.controller;

import com.vitorsilvafranca.openai_chatgpt.service.ChatGPTService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatGPTController {

    @PostMapping("/call/GPT")
    public void callGPT() throws Exception {
        new ChatGPTService().callGPT();
    }

}
