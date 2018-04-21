package com.paazl.rest;


import com.paazl.service.ShepherdService;
import com.paazl.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import java.math.BigInteger;

@Path("/shepherdmanager")
@Service
public class ShepherdResource {

    @Autowired
    ShepherdService service;

    @GET
    @Path("/balance")
    public BigInteger getBalance() {
        return service.getBalance();
    }

    @GET
    @Path("/sheepConditions")
    public String getSheepStatuses() {
        return ResponseUtils.formatOfSheepCondResponse(service.getSheepStatusses().getNumberOfDeadSheep()
                , service.getSheepStatusses().getNumberOfHealthySheep());

    }

    @GET
    @Path("/orderNewSheeps")
    public String orderNewSheep(@QueryParam("nofSheepDesired") int nofSheepDesired) {
        return service.orderNewSheep(nofSheepDesired);
    }

}
