package com.m2i.servicewebmovieapi.dao;


import com.m2i.servicewebmovieapi.SessionHelper;
import com.m2i.servicewebmovieapi.entity.Genre;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elouf
 */
public class DaoGenre {
    
    
    
      //---------------------------------------------------------Create---------------------------------------------------------------------------------------
 
        public void create(Genre genreTocreat) {
        // On vérifie les données que l'on reçoit en paramètre
        if (genreTocreat == null) {
            System.out.println("L'objet genre ne peut pas être null");
            return;
        }
        
//        if(genreTocreat.hasAFieldEmpty()){
//            throw new BadRequestException("All the fields must be filled");
//        }
        
        EntityManager entityManager = SessionHelper.getEntityManager();
        // On déclare notre transaction avec pour valeur par défaut null
        EntityTransaction tx = null;
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(genreTocreat);
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
        
       

    public void update(int id, Genre genreData) {
        
        EntityManager entityManager = SessionHelper.getEntityManager();
        
        // On récupère le role qu'on souhaite modifier
        Genre genreToUpdate = entityManager.find(Genre.class, id);
        
        // Si le role n'existe pas on ne fait pas d'update
        
        if (genreToUpdate == null) {
            throw new NotFoundException(" Genre avec l'id:" + id + " n'existe pas");
        }
        
        
        // on set les données uniquement si elle ne sont pas null
        genreToUpdate.copy(genreData);
        EntityTransaction tx = null;
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(genreToUpdate);
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
        Genre genreToDelete = entityManager.find(Genre.class, id);
        
        if (genreToDelete == null) {
           throw new NotFoundException(" Genre avec l'id:" + id + " n'existe pas");
        }
        
        EntityTransaction et = null;
        try {
            et = entityManager.getTransaction();
            et.begin();
            entityManager.remove(genreToDelete);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            throw e;
        } 
    }

    
//----------------------------------------------findAll------------------------------------------------------------------------------------------------------
       
    
    
    
    public List<Genre> findAll(){
        EntityManager entityManager = SessionHelper.getEntityManager();
        Query findAllQuery = entityManager.createQuery("select g from Genre g");
        return findAllQuery.getResultList();
        

    }
    

//----------------------------------------------FindById------------------------------------------------------------------------------------------------
    
    
     public Genre findById(int id) {
        EntityManager entityManager = SessionHelper.getEntityManager();
        Genre genreFound = entityManager.find(Genre.class, id);
        if (genreFound == null) {
            System.out.println("Attention Genre avec l'id: " + id + " n'existe pas !");
        }
        return genreFound;
    }    
    
    
}
