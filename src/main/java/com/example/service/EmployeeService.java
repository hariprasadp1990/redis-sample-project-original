package com.example.service;

import com.example.model.Employee;
import com.example.model.RedisThreadStatus;
import com.example.repository.EmployeeRepository;
import com.example.repository.RedisThreadStatusRepository;
import com.example.service.dto.EmployeeDTO;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.springframework.scheduling.annotation.Scheduled;

@Service("employeeService")
public class EmployeeService {
    

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RedisThreadStatusRepository redisThreadStatusRepository;


    public final String hashReference= "Employee";

    @Resource(name="redisTemplate")          // 'redisTemplate' is defined as a Bean in AppConfig.java
    public HashOperations<String,Integer, EmployeeDTO> redisTemplate;


    
    public void saveEmployeeToRadis(EmployeeDTO emp) {
        //creates one record in Redis DB if record with that Id is not present
        redisTemplate.put(hashReference,emp.getId(), emp);
        
    }

    public void addEmployee(Employee employee) {
        Employee emp1= employeeRepository.save(employee);
        System.out.println("saved emp>>>>>>>>>>>>"+emp1.getId());
        //saveEmployeeToRadis(emp1);
      }


      @Scheduled(cron = "0 0/2 * 1/1 * ?")
      public void refreshRedisData() throws Exception {
          System.out.println("refreshRedisData job run?????????????????");
          RedisThreadStatus thread=new RedisThreadStatus();
        List<RedisThreadStatus> threadStatus=  redisThreadStatusRepository.findById(1);
        if(threadStatus!=null&&threadStatus.size()>0)
         thread= threadStatus.get(0);
        List<EmployeeRepository.EmployeeList> employeeList = null;
        employeeList = employeeRepository.findAllEmployee(thread.getLastRunDateTime());
        System.out.println("refreshRedisData employeeList run?????????????????"+employeeList);
        Iterator<EmployeeRepository.EmployeeList> it= employeeList.iterator();
        while(it.hasNext())
        {
            EmployeeDTO empDTO=new EmployeeDTO();
            EmployeeRepository.EmployeeList  employee=  it.next();
            empDTO.setId(employee.getId());
            empDTO.setName(employee.getEmployeeName());
            empDTO.setDepartmentName(employee.getDepartmentName());
            empDTO.setAddress(employee.getAdddress());

            saveEmployeeToRadis(empDTO);
        }

       
        thread.setLastRunDateTime(String.valueOf(new Timestamp(System.currentTimeMillis())));
        redisThreadStatusRepository.save(thread);


      }




}