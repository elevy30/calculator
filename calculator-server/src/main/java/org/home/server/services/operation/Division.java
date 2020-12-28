package org.home.server.services.operation;


import org.home.server.error.CalculatorException;
import org.springframework.stereotype.Component;

@Component
public class Division implements Operation{
    @Override
    public String run(String leftOperand, String operator, String rightOperand) {
        if(Double.parseDouble(rightOperand) == 0){
            throw new CalculatorException("dividing by Zero is not prohibit!!");
        }
        return "" + (Double.parseDouble(leftOperand) / Double.parseDouble(rightOperand));
    }

    @Override
    public String getSign() {
        return "/";
    }
}
