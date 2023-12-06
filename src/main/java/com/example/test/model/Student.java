package com.example.test.model;


import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first-name")
    private String firstName;
    @Column(name = "last-name")
    private String lastName;
    private int age;

    private String password;

    public Student(String firstName, String lastName, int age){
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
    }


}
