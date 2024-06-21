package nani.microservices.department_service.service;

import nani.microservices.department_service.dto.DepartmentDto;
import nani.microservices.department_service.entity.Department;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto department);
    DepartmentDto getDepartmentByCode(String DeptCode);
}
