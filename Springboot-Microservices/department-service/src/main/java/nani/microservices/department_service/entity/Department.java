package nani.microservices.department_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "departments" )
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  departmentName;
    private String departmentDescription;
    @Column(nullable = false, unique = true)

    private String departmentCode;
}
