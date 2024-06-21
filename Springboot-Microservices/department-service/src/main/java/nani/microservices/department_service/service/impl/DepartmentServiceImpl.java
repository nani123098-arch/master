package nani.microservices.department_service.service.impl;

import lombok.AllArgsConstructor;
import nani.microservices.department_service.dto.DepartmentDto;
import nani.microservices.department_service.entity.Department;
import nani.microservices.department_service.repository.DepartmentRepository;
import nani.microservices.department_service.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final ModelMapper modelMapper;
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        //Converting DepartmentDto to Department class

      Department department = modelMapper.map(departmentDto, Department.class);

        Department department1 = departmentRepository.save(department);
        System.out.println(department.getDepartmentName());

        //Converting to Department Dto back from Department entity

        DepartmentDto departmentDto1 = modelMapper.map(department1, DepartmentDto.class);

        return departmentDto1;
    }


    public DepartmentDto getDepartmentByCode(String DeptCode) {

        Department department = departmentRepository.findByDepartmentCode(DeptCode);

        //Converting to Department DTO

        DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
        return departmentDto;
    }

}
