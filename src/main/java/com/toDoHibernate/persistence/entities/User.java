package com.toDoHibernate.persistence.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;



    // TODO Agregar la busqueda por id para recuperar las colecciones
    // @OneToMany
    // private List<GroupList> groupsList = new ArrayList<>();

    // TODO Agregar la busqueda por id para recuperar las colecciones
    // @ManyToMany
    // private List<ListTasks> listTasks = new ArrayList<>();

    // Constructors

    public User() {}

    public User(Long id, String nickname, String mail, String password) {
        this.id = id;
        this.nickname = nickname;
        this.email = mail;
        this.password = password;
    }

    // Getters y Setters

//    public List<ListTasks> getListTasks() {
//        return listTasks;
//    }
//
//    public void setListTasks(List<ListTasks> listTasks) {
//        this.listTasks = listTasks;
//    }
//
//    public List<GroupList> getGroupsList() {
//        return groupsList;
//    }
//
//    public void setGroupsList(List<GroupList> groupsList) {
//        this.groupsList = groupsList;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMail() {
        return email;
    }

    public void setMail(String mail) {
        this.email = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
