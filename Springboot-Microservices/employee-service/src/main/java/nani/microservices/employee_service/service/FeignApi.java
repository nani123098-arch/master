package nani.microservices.employee_service.service;

import nani.microservices.employee_service.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:9898", value = "DEPARTMENT-SERVICE")
public interface FeignApi {

    @GetMapping("api/dept/{deptcode}")
    public DepartmentDto getDepartment(@PathVariable String deptcode) ;

}
