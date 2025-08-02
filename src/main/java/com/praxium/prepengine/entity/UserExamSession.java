package com.praxium.prepengine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
@Table(name = "USER_EXAM_SESSIONS")
@Getter
@Setter
public class UserExamSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @Column(length = 20)
    private String status = "in_progress"; // Consider an Enum here

    private Integer score; //todo size ??

    @CreationTimestamp
    private LocalDateTime startedAt;


    private LocalDateTime completedAt;
}