package com.praxium.prepengine.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String googleId;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String photoUrl;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private Set<UserProductProgress> productProgresses;

    @OneToMany(mappedBy = "user")
    private Set<UserAnswer> answers;

    @OneToMany(mappedBy = "user")
    private Set<UserQuestionSrs> srsProgress;

    @OneToMany(mappedBy = "user")
    private Set<UserDailyActivity> dailyActivities;

    @OneToMany(mappedBy = "user")
    private Set<UserExamSession> examSessions;
}