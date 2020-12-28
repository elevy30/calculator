package org.home.server.services.operation;


public interface Operation {
    String run(String leftOperand, String operator, String rightOperand);

    String getSign();
}
