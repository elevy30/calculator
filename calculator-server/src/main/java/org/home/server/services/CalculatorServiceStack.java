package org.home.server.services;

import lombok.extern.slf4j.Slf4j;
import org.home.server.model.Equation;
import org.home.server.services.operation.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Stack;


@Slf4j
@Service
public class CalculatorServiceStack implements CalculatorService {

    Calculator calculator;

    Stack<String> operands = new Stack<>();
    Stack<String> operators = new Stack<>();

    @Autowired
    public CalculatorServiceStack(Calculator calculator) {
        this.calculator = calculator;
    }


    @Override
    public String exec(Equation equation) {
        log.info("############ Calculator Exec Serving Request " + Thread.currentThread().getName() + " #############");

        String equationStr = equation.getEquation();

        equationStr = equationStr.replaceAll("\\s","");

        String[] equationArray = (equationStr.split("((?<=[*/+-])|(?=[*/+-]))"));

        for (String element : equationArray) {
            if (isOperand(element)) {
                operands.push(element);
            } else if (isOperator(element)) {
                while (!operators.empty()) {
                    String prevOperator = operators.peek();
                    if (shouldCalcPrev(prevOperator, element)) {
                        calc();
                    } else {
                        break;
                    }
                }
                operators.push(element);
            }
        }

        while(!operators.empty() ) {
            calc();
        }

        String result = operands.pop();

        log.info("Result = {}", result);

        return result;
    }

    private void calc() {
        String operator = operators.pop();
        String rightOperand = operands.pop();
        String leftOperand = operands.pop();
        String result = calculator.run(leftOperand, operator, rightOperand);
        operands.push(result);
    }

    private boolean isOperand(String element) {
        try {
            double val = Double.parseDouble(element);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    private boolean shouldCalcPrev(String prevOperator, String element) {
        if(element.equals("*") || element.equals("/")) {
            if( prevOperator.equals("+") || prevOperator.equals("-")){
                return false;
            }
        }
        return true;
    }

    private boolean isOperator(String element) {
        return element.equals("+") || element.equals("-") || element.equals("*") || element.equals("/");
    }
}
