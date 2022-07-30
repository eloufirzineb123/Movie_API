/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m2i.servicewebmovieapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String nickname;

    @Column
    @JsonProperty(value= "password", access=JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Role", nullable = false)
    private Role role;

    public void copy(User userData) {
        if (userData.getEmail() != null) {
            this.email = userData.getEmail();
        }
        if (userData.getFirstname() != null) {
            this.firstname = userData.getFirstname();
        }

        if (userData.getLastname() != null) {
            this.lastname = userData.getLastname();
        }

        if (userData.getNickname() != null) {
            this.nickname = userData.getNickname();
        }

        if (userData.getPassword() != null) {
            this.password = userData.getPassword();
        }

        if (userData.getRole() != null) {
            this.role = userData.getRole();
        }
    }

}
