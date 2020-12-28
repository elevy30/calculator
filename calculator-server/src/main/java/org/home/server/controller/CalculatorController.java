package org.home.server.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ExampleProperty;
import lombok.extern.slf4j.Slf4j;
import org.home.server.model.Equation;
import org.home.server.services.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@RestController
//@RequestMapping(value = "/equation")
//@CrossOrigin(origins = "*", maxAge = 3600)
public class CalculatorController {


    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(@Qualifier("calculatorServiceStack") CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "equation",
                    dataType = "Equation",
                    value = "\"5*34+4\"",
                    examples = @io.swagger.annotations.Example(
                            value = {
                                    @ExampleProperty(
                                            value = "\"5*34+4\"",
                                            mediaType = "application/json")
                            }
                    )
            )
    })
    @RequestMapping(path = "/calc", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> execEquation(@RequestBody Equation equation) {
        String result;
        try {
            log.info("get request for exe equation : {}", equation);
            //TODO - validate equation (ids exists, order, signs , ....)
            result = calculatorService.exec(equation);

//            HttpHeaders responseHeaders = new HttpHeaders();
//            responseHeaders.set("Access-Control-Allow-Origin", "*");
//            responseHeaders.set("Access-Control-Allow-Methods", "POST, GET");
//            responseHeaders.set("Access-Control-Allow-Headers", "Content-Type, Authorization");
//            return new ResponseEntity<>(result, responseHeaders, HttpStatus.OK);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Equation Exec Failed", e);
        }
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test() {
        try {
            log.info("TEST");

            return new ResponseEntity<>("test", HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Equation Exec Failed", e);
        }
    }

}

