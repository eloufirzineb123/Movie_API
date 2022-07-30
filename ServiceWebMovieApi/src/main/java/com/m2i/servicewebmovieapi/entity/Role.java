package com.m2i.servicewebmovieapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "roles")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String identifiant;

    @Column
    private String description;

    public void copy(Role roleData) throws Exception {

        if (roleData.getDescription() != null) {
            this.description = roleData.getDescription();
        }

        if (roleData.getIdentifiant() != null) {
            this.identifiant = roleData.getIdentifiant();
        }
    }

}
