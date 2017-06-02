package com.xy.inc.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xy.inc.service.InterestPointService;
import com.xy.inc.domain.InterestPoint;
import com.xy.inc.exception.ValidationException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Matheus Xavier
 */
@Controller
@RequestMapping("/poi")
@Transactional
public class InterestPointController {

    @Autowired
    private InterestPointService interestPointService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@Valid @RequestBody InterestPoint interestPoint, BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        interestPointService.save(interestPoint);

        return new ResponseEntity(interestPoint, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listAll() {
        List<InterestPoint> interestPoints = interestPointService.listAll();

        if (interestPoints.isEmpty()) {
            return buildNotContentResponse();
        }
        
        return new ResponseEntity(interestPoints, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/upcoming")
    public ResponseEntity listUpcoming(@RequestParam("x") Integer x, @RequestParam("y") Integer y, @RequestParam("d-max") Integer maxDistance) {
        List<InterestPoint> interestPoints = interestPointService.listUpcoming(x, y, maxDistance);

        if (interestPoints.isEmpty()) {
            return buildNotContentResponse();
        }

        return new ResponseEntity(interestPoints, HttpStatus.OK);
    }

    private ResponseEntity buildNotContentResponse() {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
