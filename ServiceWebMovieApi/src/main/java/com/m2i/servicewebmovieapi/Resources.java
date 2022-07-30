/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m2i.servicewebmovieapi;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author elouf
 */
@Path("resources")
public class Resources {

    @GET
    public String testSessionHelper() {
        System.out.println("hello");
      EntityManager entityManager = SessionHelper.getEntityManager();
        return "hello hello";
    }

    
    
}
