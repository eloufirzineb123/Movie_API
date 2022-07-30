package com.m2i.servicewebmovieapi.dao;

import com.m2i.servicewebmovieapi.SessionHelper;
import com.m2i.servicewebmovieapi.entity.Comment;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author elouf
 */
public class DaoComment {

    //---------------------------------------------------------Create---------------------------------------------------------------------------------------
    public void create(Comment commentTocreat) {
        // On vérifie les données que l'on reçoit en paramètre
        if (commentTocreat == null) {
            System.out.println("L'objet actor ne peut pas être null");
            return;
        }

//        if(ActorTocreat.hasAFieldEmpty()){
//            throw new BadRequestException("All the fields must be filled");
//        }
        EntityManager entityManager = SessionHelper.getEntityManager();
        // On déclare notre transaction avec pour valeur par défaut null
        EntityTransaction tx = null;
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(commentTocreat);
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
    public void update(int id, Comment commentData) {

        EntityManager entityManager = SessionHelper.getEntityManager();

        // On récupère le role qu'on souhaite modifier
        Comment commentToUpdate = entityManager.find(Comment.class, id);

        // Si le role n'existe pas on ne fait pas d'update
        if (commentToUpdate == null) {
            throw new NotFoundException(" comment avec l'id:" + id + " n'existe pas");
        }

        // on set les données uniquement si elle ne sont pas null
        commentToUpdate.copy(commentData);
        EntityTransaction tx = null;
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(commentToUpdate);
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
        Comment commentToDelete = entityManager.find(Comment.class, id);

        if (commentToDelete == null) {
            throw new NotFoundException(" comment avec l'id:" + id + " n'existe pas");
        }

        EntityTransaction et = null;
        try {
            et = entityManager.getTransaction();
            et.begin();
            entityManager.remove(commentToDelete);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            throw e;
        }
    }

//----------------------------------------------findAll------------------------------------------------------------------------------------------------------
    public List<Comment> findAll() {
        EntityManager entityManager = SessionHelper.getEntityManager();
        Query findAllQuery = entityManager.createQuery("select c from Comment c");
        return findAllQuery.getResultList();

    }

//----------------------------------------------FindById------------------------------------------------------------------------------------------------
    public Comment findById(int id) {
        EntityManager entityManager = SessionHelper.getEntityManager();
        Comment commentFound = entityManager.find(Comment.class, id);
        if (commentFound == null) {
            System.out.println("Attention Actor avec l'id: " + id + " n'existe pas !");
        }
        return commentFound;
    }

}
