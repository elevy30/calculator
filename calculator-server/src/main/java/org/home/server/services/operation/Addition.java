package org.home.server.services.operation;


import org.springframework.stereotype.Component;

@Component
public class Addition implements Operation {
    @Override
    public String run(String leftOperand, String operator, String rightOperand) {
        return "" + (Double.parseDouble(leftOperand) + Double.parseDouble(rightOperand));
    }

    @Override
    public String getSign() {
        return "+";
    }
}
