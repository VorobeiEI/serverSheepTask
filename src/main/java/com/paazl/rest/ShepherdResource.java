package com.paazl.rest;

import com.paazl.service.SheepStatussesDto;
import com.paazl.service.ShepherdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import java.math.BigInteger;

@Path("/shepherdmanager")
@Service
//@RestController
//@RequestMapping("/shepherdmanager")
public class ShepherdResource {

    @Autowired
    ShepherdService service;

    @GET
    @Path("/balance")
    //@GetMapping("/balance")
    public BigInteger getBalance() {
        return service.getBalance();
    }

    @GET
    @Path("/sheepStatusses")
//    @GetMapping("/sheepStatusses")
    public String getSheepStatuses() {
        return "Number of dead sheep: " + service.getSheepStatusses().getNumberOfDeadSheep() + '\n' +
                "Number of healthy sheep: " + service.getSheepStatusses().getNumberOfHealthySheep();

    }

    @GET
    @Path("/orderNewSheeps/{id}")
    public Integer orderNewSheep(@PathParam("id") int a) {

        return a;
    }

}
