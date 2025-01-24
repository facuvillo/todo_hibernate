package com.toDoHibernate.persistence.entities;

import jakarta.persistence.*;

import java.util.Date;

// TODO Revisar las colecciones y relaciones que voy a usar
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(name = "task_list", nullable = false, length = 100)
    private String title;

    @Column(name = "task_description", length = 500)
    private String description;

    @Column(name = "due_date")
    private Date dueDate;

    private Boolean isImportant;

    public Task() {}

    public Task(Long id, String title, String description, Date dueDate, Boolean isImportant) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isImportant = isImportant;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getImportant() {
        return isImportant;
    }

    public void setImportant(Boolean important) {
        isImportant = important;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", isImportant=" + isImportant +
                '}';
    }
}
