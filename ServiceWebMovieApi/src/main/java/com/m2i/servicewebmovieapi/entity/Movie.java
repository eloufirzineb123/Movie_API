package com.m2i.servicewebmovieapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name = "movies")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date releaseDate;

    private int duration;

    private float rating;

    private String synopsie;

    private String originCountry;
    @ManyToMany
    @JoinTable(name = "Movie_Genre",
            joinColumns = @JoinColumn(name = "idMovie"),
            inverseJoinColumns = @JoinColumn(name = "idGenre"))
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors = new ArrayList<>();

    @Column
    @ElementCollection
    @CollectionTable(name = "languages", joinColumns = @JoinColumn(name = "idMovie"))
    private List<String> languages;

    @OneToMany(mappedBy = "movie")
    private List<Comment> comments = new ArrayList<>();

    public void copy(Movie movieData) {
        if (movieData.getActors() != null) {
            this.actors = movieData.getActors();
        }

        if (movieData.getComments() != null) {
            this.comments = movieData.getComments();
        }

        if (movieData.getGenres() != null) {
            this.genres = movieData.getGenres();
        }

        if (movieData.getLanguages() != null) {
            this.languages = movieData.getLanguages();
        }

        if (movieData.getName() != null) {
            this.name = movieData.getName();
        }

        if (movieData.getOriginCountry() != null) {
            this.originCountry = movieData.getOriginCountry();
        }

        if (movieData.getSynopsie() != null) {
            this.synopsie = movieData.getSynopsie();
        }

        this.duration = movieData.getDuration();

        this.releaseDate = movieData.getReleaseDate();

        this.rating = movieData.getRating();

    }
}
