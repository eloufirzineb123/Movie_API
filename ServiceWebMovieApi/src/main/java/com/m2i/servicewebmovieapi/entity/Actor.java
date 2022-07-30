/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m2i.servicewebmovieapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "actors")
public class Actor implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String firstname;
    
    @Column
    private String lastname;
    
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition ="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date birthdate;
    
    
    @ManyToMany
    @JoinTable(name = "T_Actor_movie_Associations",
            joinColumns = @JoinColumn(name = "id_Actor"),
            inverseJoinColumns = @JoinColumn(name = "id_Movie"))
    private List<Movie> movies = new ArrayList<>();
    
    
    
    public void copy(Actor actordata) {
        if (actordata.getBirthdate() != null) {
            this.birthdate = actordata.getBirthdate();
        }
        
        if (actordata.getFirstname()!= null) {
            this.firstname = actordata.getFirstname();
        }
        if (actordata.getLastname()!= null) {
            this.lastname = actordata.getLastname();
        }
        
        if (actordata.getMovies()!= null) {
            this.movies = actordata.getMovies();
        }
    }

}
