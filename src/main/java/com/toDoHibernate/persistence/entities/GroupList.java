package com.toDoHibernate.persistence.entities;

import jakarta.persistence.*;

// TODO Revisar las colecciones y relaciones que voy a usar
@Entity
@Table(name = "groups_list")
public class GroupList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name")
    private String name;

    @Column(name = "group_description")
    private String description;

    public GroupList() {}

    public GroupList(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
