package com.toDoHibernate.persistence.entities;

import jakarta.persistence.*;

// TODO Revisar las colecciones y relaciones que voy a usar
@Entity
@Table(name = "lists")
public class ListTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private Long id;

    @Column(name = "list_title", nullable = false, length = 50)
    private String title;

    @Column(name = "list_description", length = 500)
    private String description;

    public ListTasks() {}

    public ListTasks(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
