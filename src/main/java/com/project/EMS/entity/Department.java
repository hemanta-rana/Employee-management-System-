package com.project.EMS.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "department_name",nullable = false,unique = true)
    private String departmentName;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt ;

    // relation -- one to many (one department can have multiple employees ))
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees;

}
