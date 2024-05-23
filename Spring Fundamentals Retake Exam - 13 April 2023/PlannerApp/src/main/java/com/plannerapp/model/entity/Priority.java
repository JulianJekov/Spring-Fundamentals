package com.plannerapp.model.entity;

import com.plannerapp.model.enums.PriorityName;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PriorityName name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "priority")
    private List<Task> tasks;

    public PriorityName getName() {
        return name;
    }

    public Priority setName(PriorityName name) {
        this.name = name;
        setDescription(name);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Priority setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Priority setTasks(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    private void setDescription(PriorityName name) {

        String description = "";

        switch (name) {
            case LOW -> description = "Should be fixed if time permits but can be postponed.";
            case URGENT -> description = "An urgent problem that blocks the system use until the issue is resolved.";
            case IMPORTANT ->
                    description = "A core functionality that your product is explicitly supposed to perform is compromised.";
        }
        this.description = description;
    }

}
