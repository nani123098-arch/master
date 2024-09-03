package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pack.Employee;
import services.ProducerClass;

@RestController
@RequestMapping("/api/v1")
public class ControllerClass {

    @Autowired
    private ProducerClass producerClass;


    @PostMapping("save")
    public String sendEmployee(@RequestBody Employee employee)
    {
//        System.out.println(employee.toString());
        producerClass.sendEvent(employee);

        return "data sent successfully";
    }

    @GetMapping("get")
    public String getSomeExample()
    {
        return "getSomething";
    }


}
