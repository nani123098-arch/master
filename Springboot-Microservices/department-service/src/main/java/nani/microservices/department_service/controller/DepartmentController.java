package nani.microservices.department_service.controller;

import lombok.AllArgsConstructor;
import nani.microservices.department_service.dto.DepartmentDto;
import nani.microservices.department_service.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/dept")
public class DepartmentController {
    private DepartmentService departmentService;

    //Create an department in db
    @PostMapping("create")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto)
    {
        DepartmentDto departmentDto1 = departmentService.saveDepartment(departmentDto);

        return new ResponseEntity<>(departmentDto1, HttpStatus.CREATED);
    }

    @GetMapping("{deptcode}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable  String deptcode)
    {
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(deptcode);

        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }
}
