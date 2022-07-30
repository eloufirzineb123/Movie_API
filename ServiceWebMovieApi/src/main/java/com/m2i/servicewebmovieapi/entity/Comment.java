package com.m2i.servicewebmovieapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "Comments")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_User", nullable = false)
    private User user;

    @Column
    private String content;

    
    @ManyToOne
    @JoinColumn(name = "id_Movie", nullable = false)
    private Movie movie;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition ="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date creationDate;
    
    public void copy(Comment dataComment){
        if(dataComment.getUser()!=null){
            this.user=dataComment.getUser();
        }
        
        if(dataComment.getContent()!=null){
            this.content=dataComment.getContent();
        }
        if(dataComment.getMovie()!=null){
            this.movie=dataComment.getMovie();
        }
        
        if(dataComment.getCreationDate()!=null){
            this.creationDate=dataComment.getCreationDate();
        }
        
             
    }

}
