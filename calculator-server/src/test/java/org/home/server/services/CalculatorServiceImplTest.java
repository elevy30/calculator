package org.home.server.services;

import org.home.server.error.CalculatorException;
import org.home.server.model.Equation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigTest.class, loader = AnnotationConfigContextLoader.class)
public class CalculatorServiceImplTest {

    @Autowired
    @Qualifier("calculatorServiceStack")
    CalculatorService calculatorService;

    @Test
    public void simpleTest(){
        Equation equation = new Equation("10+4*5/2");
        String exec = calculatorService.exec(equation);
        assertEquals(Double.parseDouble("20.0"), Double.parseDouble(exec), 0.001d);

        equation = new Equation("200 * 1 + 8 / 2");
        exec = calculatorService.exec(equation);
        assertEquals(Double.parseDouble("204.0"), Double.parseDouble(exec), 0.001d);

        equation = new Equation("11/35*5+2+12");
        exec = calculatorService.exec(equation);
        assertEquals(Double.parseDouble("15.57142"), Double.parseDouble(exec), 0.001d);

    }

    @Test(expected = CalculatorException.class)
    public void zeroTest() {
        Equation equation = new Equation("11/0*5+2+12");
        String exec = calculatorService.exec(equation);
        assertEquals(Double.parseDouble("15.57142"), Double.parseDouble(exec), 0.001d);
    }

    @Test
    public void divideTest() {
        Equation equation = new Equation("100/2/2");
        String exec = calculatorService.exec(equation);
        assertEquals(Double.parseDouble("25.0"), Double.parseDouble(exec), 0.001d);
    }
}