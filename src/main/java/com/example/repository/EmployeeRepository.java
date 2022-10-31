package com.example.repository;
import com.example.model.Employee;
import com.example.service.dto.EmployeeDTO;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.redis.core.HashOperations;
import javax.annotation.Resource;
import java.io.Serializable;

@Repository("employeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

   

    public List<Employee> findById(String id);


    public Optional<Employee> findByName(String name);
    
      /**
     * getCityGroupDetails
     * @param id
     * @return 
     */
    
    @Query(value = "select emp.id, emp.name as EmployeeName,d.name as departmentName,emp.address as address" +
                    
                    " from Employee emp " +
                    "left join department d on d.id = emp.department_id where (emp.created_date_time>=:lastRunDateTime or emp.updated_date_time>=:lastRunDateTime)", nativeQuery = true)
    List<EmployeeList> findAllEmployee(@Param("lastRunDateTime") String lastRunDateTime);

    public interface EmployeeList extends Serializable {

        int getId();

        String getEmployeeName();
        
        String getDepartmentName();
        String getAdddress();
        
        
    }
    
}
