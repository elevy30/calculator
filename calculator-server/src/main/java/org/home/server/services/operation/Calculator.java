package org.home.server.services.operation;

import org.home.server.error.CalculatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Component
public class Calculator implements Operation {

    ApplicationContext applicationContext;

    Map<String, Operation> operators = new HashMap<>();

    @Autowired
    public Calculator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init(){
        Map<String, Operation> operatorsMap = applicationContext.getBeansOfType(Operation.class);
        operatorsMap.forEach((key, operation) -> operators.put(operation.getSign(), operation));

    }


    public String run(String leftOperand, String operator, String rightOperand) {
        String result = "";
        Operation operation = operators.get(operator);
        if(operation != null){
            result = operation.run(leftOperand, operator, rightOperand);
        }
        return result;
    }

    @Override
    public String getSign() {
        return "";
    }
}
