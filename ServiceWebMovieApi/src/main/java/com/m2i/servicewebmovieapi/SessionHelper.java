/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m2i.servicewebmovieapi;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;



public class SessionHelper {
    

    private static EntityManager entityManager;
    
  
     
   public static EntityManager getEntityManager() {
        if (entityManager == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("rv02_movieapi");
            entityManager = emf.createEntityManager();
            //System.out.println("hello zineb");
        }
         
        return entityManager;
    }
        
     
}

