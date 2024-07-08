package com.ganeshgc.socialmediablog_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}
