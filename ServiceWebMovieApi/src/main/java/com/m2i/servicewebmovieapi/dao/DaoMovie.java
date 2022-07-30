/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m2i.servicewebmovieapi.dao;

import com.m2i.servicewebmovieapi.SessionHelper;
import com.m2i.servicewebmovieapi.entity.Movie;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author elouf
 */
public class DaoMovie {
    

      //---------------------------------------------------------Create---------------------------------------------------------------------------------------
 
        public void create(Movie movieTocreat) {
        // On vérifie les données que l'on reçoit en paramètre
        if (movieTocreat == null) {
            System.out.println("L'objet genre ne peut pas être null");
            return;
        }
        
//        if(movieTocreat.hasAFieldEmpty()){
//            throw new BadRequestException("All the fields must be filled");
//        }
        
        EntityManager entityManager = SessionHelper.getEntityManager();
        // On déclare notre transaction avec pour valeur par défaut null
        EntityTransaction tx = null;
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(movieTocreat);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la création");
            if (tx != null) {
                // Une erreur est survenue, on discard les actions entamés dans la transaction
                tx.rollback();
            }
            throw e;
        }
    }
        
        
    
      //---------------------------------------------------Update---------------------------------------------------------------------------------------------
        
       

    public void update(int id, Movie movieData) {
        
        EntityManager entityManager = SessionHelper.getEntityManager();
        
        // On récupère le role qu'on souhaite modifier
        Movie movieToUpdate = entityManager.find(Movie.class, id);
        
        // Si le role n'existe pas on ne fait pas d'update
        
        if (movieToUpdate == null) {
            throw new NotFoundException(" Genre avec l'id:" + id + " n'existe pas");
        }
        
        
        // on set les données uniquement si elle ne sont pas null
        movieToUpdate.copy(movieData);
        EntityTransaction tx = null;
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(movieToUpdate);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la modification");
            if (tx != null) {
                tx.rollback();
            }
            throw e;  // On relance le catch vers UserResource
        }
        
    }
    
    
    //-----------------------------------------------Delete-------------------------------------------------------------------------------------------------
    
    
    

    public void delete(int id) {

        EntityManager entityManager = SessionHelper.getEntityManager();
        
        //On récupère le utilisateur qu'on souhaite supprimer
        Movie movieToDelete = entityManager.find(Movie.class, id);
        
        if (movieToDelete == null) {
           throw new NotFoundException(" Movie avec l'id:" + id + " n'existe pas");
        }
        
        EntityTransaction et = null;
        try {
            et = entityManager.getTransaction();
            et.begin();
            entityManager.remove(movieToDelete);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            throw e;
        } 
    }

    
//----------------------------------------------findAll------------------------------------------------------------------------------------------------------
       
    
    
    
    public List<Movie> findAll(){
        EntityManager entityManager = SessionHelper.getEntityManager();
        Query findAllQuery = entityManager.createQuery("select m from Movie m");
        return findAllQuery.getResultList();
        

    }
    

//----------------------------------------------FindById------------------------------------------------------------------------------------------------
    
    
     public Movie findById(int id) {
        EntityManager entityManager = SessionHelper.getEntityManager();
        Movie movieFound = entityManager.find(Movie.class, id);
        if (movieFound == null) {
            System.out.println("Attention Movie avec l'id: " + id + " n'existe pas !");
        }
        return movieFound;
    }    
    
    
}
