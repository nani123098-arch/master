package nani.microservices.employee_service.service.impl;

import lombok.AllArgsConstructor;
import nani.microservices.employee_service.dto.ApiResponse;
import nani.microservices.employee_service.dto.DepartmentDto;
import nani.microservices.employee_service.dto.EmployeeDto;
import nani.microservices.employee_service.entity.Employee;
import nani.microservices.employee_service.repository.EmployeeRepository;
import nani.microservices.employee_service.service.EmployeeService;
import nani.microservices.employee_service.service.FeignApi;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final ModelMapper modelMapper;
    private EmployeeRepository employeeRepository;
    //private RestTemplate restTemplate;
//    private WebClient webClient;

    private FeignApi feignApi;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        //Convert to entity
        Employee employee = modelMapper.map(employeeDto, Employee.class);

        Employee employee1 = employeeRepository.save(employee);

        EmployeeDto employeeDto1 = modelMapper.map(employee1, EmployeeDto.class);



        return employeeDto1;
    }

    @Override
    public ApiResponse getById(Long id) {

        Employee employee = employeeRepository.findById(id).get();

        //convert to Dto
//       DepartmentDto departmentDto = webClient.get().uri("http://localhost:9898/api/dept/" +
//               employee.getDepartmentCode()).retrieve().bodyToMono(DepartmentDto.class).block();

        //Using OPen Feighclient

        DepartmentDto departmentDto =  feignApi.getDepartment(employee.getDepartmentCode());

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setEmployeeDto(employeeDto);
        apiResponse.setDepartmentDto(departmentDto);

        return apiResponse;
    }
}
