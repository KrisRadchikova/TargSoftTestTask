package by.targsoft.test.targsofttest.service.interfaces;

import by.targsoft.test.targsofttest.entity.Employee;
import by.targsoft.test.targsofttest.entity.EmployeeCategory;
import by.targsoft.test.targsofttest.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void createEmployee() {
        EmployeeCategory employeeCategory = new EmployeeCategory(BigInteger.ONE, "category", null);
        Employee employee = new Employee(BigInteger.ONE, "Kris", employeeCategory, "Kris_login", "password");

        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

        Mockito.verify(employeeRepository, Mockito.times(0)).save(employee);

        Employee saveEmployee = employeeService.createEmployee(employee);

        Assert.assertEquals(employee, saveEmployee);
        Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);
    }
}