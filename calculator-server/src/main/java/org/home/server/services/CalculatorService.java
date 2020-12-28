package org.home.server.services;


import org.home.server.model.Equation;

public interface CalculatorService {
    String exec(Equation equation);
}
