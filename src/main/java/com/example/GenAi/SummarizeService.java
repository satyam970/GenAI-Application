package com.example.GenAi;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SummarizeService {

    private ChatClient chatClient;

    private List<Message> history =new ArrayList<>();

    public SummarizeService(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }
    public String chat(String message){

        history.add(new UserMessage(message));

        String output=chatClient.prompt()
                .messages(history)
                .call()
                .content();

        history.add(new AssistantMessage(output));

        return output;

    }
}
