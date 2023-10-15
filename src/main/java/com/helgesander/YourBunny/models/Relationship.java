package com.helgesander.YourBunny.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user_relationships")
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String relationshipId;

    //private User firstUser;
    //private User secondUser;
    private String relationshipStatus;
    private boolean blockStatus;
}
