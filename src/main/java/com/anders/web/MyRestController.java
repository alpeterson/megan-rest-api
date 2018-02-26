package com.anders.web;

import com.anders.model.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anderspeterson on 2/25/18.
 */
@RestController
@RequestMapping(value = "/employee", headers = "Accept=*/*", produces = "application/json")
public class MyRestController {

    private static List<Employee> employees;

    static {
        employees = new ArrayList<Employee>();
        for(int i=0; i<10; i++) {
            employees.add(buildEmployee(new Long(i)));
        }
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getEmployees() {
        return employees;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable(value = "id") Long employeeId) {
        return employees.get(employeeId.intValue());
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
    public Long createNewEmployee(Employee employee) {
        Long newId = new Long(employees.size());
        employee.setId(newId);
        employees.add(employee);
        return newId;
    }


    private static Employee buildEmployee(Long id) {
        Employee emp = new Employee();
        emp.setId(id);
        emp.setFirstName("First Name " + id);
        emp.setLastName("Last Name " + id);
        return emp;
    }


}
