

package com.example.controller;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
//import javax.validation.Valid;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/addEmployee")
    public Map addEmployee( @RequestBody Employee employee) {
        HashMap map = null;
        try {
            employee.setCreatedDateTime(String.valueOf(new Timestamp(System.currentTimeMillis())));
            employeeService.addEmployee(employee);
            map = new HashMap<>();
            map.put("message", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("/updateEmployee")
    public Map updateEmployee( @RequestBody Employee employee) {
        HashMap map = null;
        try {
            employee.setUpdatedDateTime(String.valueOf(new Timestamp(System.currentTimeMillis())));
            employeeService.addEmployee(employee);
            map = new HashMap<>();
            map.put("message", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    
    
}