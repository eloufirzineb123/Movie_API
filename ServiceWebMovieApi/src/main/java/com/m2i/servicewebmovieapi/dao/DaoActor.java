
package com.m2i.servicewebmovieapi.dao;

import com.m2i.servicewebmovieapi.SessionHelper;
import com.m2i.servicewebmovieapi.entity.Actor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;


public class DaoActor {

    
    
      //---------------------------------------------------------Create---------------------------------------------------------------------------------------
 
        public void create(Actor actorTocreat) {
        // On vérifie les données que l'on reçoit en paramètre
        if (actorTocreat == null) {
            System.out.println("L'objet actor ne peut pas être null");
            return;
        }
        
//        if(actorTocreat.hasAFieldEmpty()){
//            throw new BadRequestException("All the fields must be filled");
//        }
        
        EntityManager entityManager = SessionHelper.getEntityManager();
        // On déclare notre transaction avec pour valeur par défaut null
        EntityTransaction tx = null;
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(actorTocreat);
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
        
       

    public void update(int id, Actor actorData) {
        
        EntityManager entityManager = SessionHelper.getEntityManager();
        
        // On récupère le role qu'on souhaite modifier
        Actor actorToUpdate = entityManager.find(Actor.class, id);
        
        // Si le role n'existe pas on ne fait pas d'update
        
        if (actorToUpdate == null) {
            throw new NotFoundException(" Actor avec l'id:" + id + " n'existe pas");
        }
        
        
        // on set les données uniquement si elle ne sont pas null
        actorToUpdate.copy(actorData);
        EntityTransaction tx = null;
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(actorToUpdate);
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
        Actor actorToDelete = entityManager.find(Actor.class, id);
        
        if (actorToDelete == null) {
           throw new NotFoundException(" Actor avec l'id:" + id + " n'existe pas");
        }
        
        EntityTransaction et = null;
        try {
            et = entityManager.getTransaction();
            et.begin();
            entityManager.remove(actorToDelete);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            throw e;
        } 
    }

    
//----------------------------------------------findAll------------------------------------------------------------------------------------------------------
       
    
    
    
    public List<Actor> findAll(){
        EntityManager entityManager = SessionHelper.getEntityManager();
        Query findAllQuery = entityManager.createQuery("select a from Actor a");
        return findAllQuery.getResultList();
        

    }
    

//----------------------------------------------FindById------------------------------------------------------------------------------------------------
    
    
     public Actor findById(int id) {
        EntityManager entityManager = SessionHelper.getEntityManager();
        Actor actorFound = entityManager.find(Actor.class, id);
        if (actorFound == null) {
            System.out.println("Attention Actor avec l'id: " + id + " n'existe pas !");
        }
        return actorFound;
    }    
    
    
}

