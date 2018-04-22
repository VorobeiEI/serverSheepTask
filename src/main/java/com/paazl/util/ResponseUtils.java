package com.paazl.util;

public class ResponseUtils {

    //method for constructing response
    public static String formatOfSheepCondResponse(int getNumberOfDeadSheep, int getNumberOfHealthySheep) {

        return "Number of dead sheep: " + getNumberOfDeadSheep +'\n'+
        "Number of healthy sheep: "+ getNumberOfHealthySheep;
    }
}
