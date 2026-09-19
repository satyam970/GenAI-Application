package com.example.GenAi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SummarizeController {
    @Autowired
    private SummarizeService summarizeService;

    @PostMapping("/chat")
    public String chat(@RequestBody String message){
        return summarizeService.chat(message);
    }
}
