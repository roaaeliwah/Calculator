package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

//entry point of web app
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/calculator")
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    @PostMapping("/calculate")
    public String evaluate(@RequestBody CalculatorRequest request) {
        double result;
        try {
            switch(request.getOperation()) {
                case "+":
                case "-":
                case "x":
                case "÷":
                    result = calculatorService.eval(request.getNum1(), request.getNum2(), request.getOperation());
                    break;
                case "square":
                    result = calculatorService.square(request.getNum1());
                    break;
                case "sqrt":
                    result = calculatorService.sqrt(request.getNum1());
                    break;
                case "reciprocal":
                    result = calculatorService.reciprocal(request.getNum1());
                    break;
                case "negate":
                    result = calculatorService.negate(request.getNum1());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operation");
            }
            //converts the exact binary value of result into a decimal to avoid really small errors
            BigDecimal formatted = new BigDecimal(result).setScale(10, RoundingMode.HALF_UP);
            // returns string with no unnecessary trailing zeros
            return formatted.stripTrailingZeros().toPlainString();
        }
        catch (ArithmeticException | IllegalArgumentException e) {
            return "E";
        }

    }
}
