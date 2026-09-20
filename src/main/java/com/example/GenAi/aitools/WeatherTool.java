package com.example.GenAi.aitools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WeatherTool {

    private final RestClient restClient;
    private final String apikey;

    public WeatherTool(RestClient.Builder builder,
                       @Value("${weather.api.key}") String apikey) {
        this.restClient = builder.baseUrl("https://api.weatherapi.com/v1").build();
        this.apikey = apikey;
    }

    @Tool(description = "Get the current weather of a city")
    public String CurrentWeather(
            @ToolParam(description = "Name of the city") String city)
    {
        System.out.println("weather tool called...");

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/current.json")
                        .queryParam("key",apikey)
                        .queryParam("q",city)
                        .build())
                .retrieve()
                .body(String.class);
    }
}
