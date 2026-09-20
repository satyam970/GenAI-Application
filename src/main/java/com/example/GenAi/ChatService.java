package com.example.GenAi;

import com.example.GenAi.aitools.CalculatorTool;
import com.example.GenAi.aitools.CurrencyExchangeTool;
import com.example.GenAi.aitools.WeatherTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private ChatClient chatClient;
    private CalculatorTool calculatorTool;
    private WeatherTool weatherTool;
    private CurrencyExchangeTool  currencyExchangeTool;

    private List<Message> history =new ArrayList<>();

    private final String SYSTEM_PROMPT=
            """
           You are helpful AI assistant with access to external tools.

           Follow these rules:
           1.For arithmetic calculations, Always use the calculator tool.
           2.After receiving tool results, explain the answer naturally.
           3.if someone asked for current weather of a city than you weather tool for it.
           """;




    public ChatService(ChatClient.Builder builder,
                       CalculatorTool calculatorTool,
                       WeatherTool weatherTool,
                       CurrencyExchangeTool currencyExchangeTool)
    {
        this.chatClient = builder.build();
        this.calculatorTool = calculatorTool;
        this.weatherTool = weatherTool;
        this.currencyExchangeTool = currencyExchangeTool;
    }
    public String chat(String message){

        history.add(new UserMessage(message));

        String output=chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .messages(history)
                .tools(calculatorTool,weatherTool,currencyExchangeTool)
                .call()
                .content();

        history.add(new AssistantMessage(output));

        return output;

    }
}
