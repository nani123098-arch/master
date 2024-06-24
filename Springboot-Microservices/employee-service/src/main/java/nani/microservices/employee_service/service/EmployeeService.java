package nani.microservices.employee_service.service;

import nani.microservices.employee_service.dto.ApiResponse;
import nani.microservices.employee_service.dto.EmployeeDto;
import nani.microservices.employee_service.entity.Employee;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    ApiResponse getById(Long id);
}
