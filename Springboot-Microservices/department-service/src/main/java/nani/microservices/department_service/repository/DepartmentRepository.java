package nani.microservices.department_service.repository;

import nani.microservices.department_service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentCode(String deptCode);
}
