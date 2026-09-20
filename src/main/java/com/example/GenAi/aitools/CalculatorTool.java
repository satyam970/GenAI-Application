package com.example.GenAi.aitools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class CalculatorTool {

    @Tool(description = """
            Performs arithmetic calculations.
            Supported operations: add,subtract,multiply, divide,mod, power.
            """)
    public double calculate(
            @ToolParam(description = "Operation: add, sub, multiply, divide, mod, power")
            String operation,
            @ToolParam(description ="first Number")
            double a,
            @ToolParam(description = "second Number")
            double b){

        System.out.println("Calculator tool called...");

        if(operation.equals("add")){
            return a+b;
        }

        else if(operation.equals("subtract")){
            return a-b;
        }
        else if(operation.equals("multiply")){
            return a*b;
        }
        else if(operation.equals("divide")){
            if(b==0){
                throw new IllegalArgumentException(" Cannot divide by zero");
            }
            return a/b;
        }

        else if(operation.equals("mod")){
            if(b==0){
                throw new IllegalArgumentException(" Cannot calculate modulo by zero");
            }
        }
        else if(operation.equals("power")){
            return Math.pow(a,b);
        }
        else{
            throw new IllegalArgumentException("Unsupported operation"+operation);
        }
        return 0;
    }
}
