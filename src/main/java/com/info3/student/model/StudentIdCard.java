package com.info3.student.model;

import jakarta.persistence.*;

@Entity
@Table(name = "studentIdCard")
public class StudentIdCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "card_number", nullable = false, unique = true, columnDefinition = "TEXT")
    private String cardNumber;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}
