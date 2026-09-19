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

    private final String SYSTEM_PROMPT=
            """
            You are customer-support executive for our Food ordering app named Homefood.
            
            Your job is to identify the customer's main problem and urgency . Answer them related to their query in one line.
            
            Use professional language. If user has an issue , use words like I understand your frustration, I am really 
            sorry for your trouble etc.
            
            Do not answer any other question which is not related to Ordering Food query, refund query, order tracking
            status query or company policy query..
           """;




    public SummarizeService(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }
    public String chat(String message){

        history.add(new UserMessage(message));

        String output=chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .messages(history)
                .call()
                .content();

        history.add(new AssistantMessage(output));

        return output;

    }
}
