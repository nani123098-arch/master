package nani.microservices.employee_service.controller;

import lombok.AllArgsConstructor;
import nani.microservices.employee_service.dto.ApiResponse;
import nani.microservices.employee_service.dto.EmployeeDto;
import nani.microservices.employee_service.entity.Employee;
import nani.microservices.employee_service.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    //Save employee
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody  EmployeeDto employeeDto)
    {
        EmployeeDto employeeDto1 = employeeService.createEmployee(employeeDto);

        return new ResponseEntity<>(employeeDto1, HttpStatus.CREATED);
    }

    //Get the employee by id
    @GetMapping("getEmployee/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable  Long id)
    {
        ApiResponse apiResponse = employeeService.getById(id);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
