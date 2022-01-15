package by.targsoft.test.targsofttest.service.interfaces;

import by.targsoft.test.targsofttest.entity.Employee;

import java.math.BigInteger;
import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Employee findEmployeeById(BigInteger id);

    List<Employee> getAllEmployees();

    Employee updateEmployee(BigInteger id, Employee employee);

    void deleteEmployeeById(BigInteger id);

}
