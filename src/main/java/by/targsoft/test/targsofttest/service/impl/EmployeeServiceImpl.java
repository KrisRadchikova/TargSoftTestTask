package by.targsoft.test.targsofttest.service.impl;

import by.targsoft.test.targsofttest.entity.Employee;
import by.targsoft.test.targsofttest.exception.NotFoundException;
import by.targsoft.test.targsofttest.repository.EmployeeRepository;
import by.targsoft.test.targsofttest.service.interfaces.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    //private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);


    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        log.info("Возврат информации о новом работнике");
        if (employeeRepository.findEmployeeByLogin(employee.getLogin()) == null) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            return employeeRepository.save(employee);
        } else throw new EntityExistsException("Employee exists with login " + employee.getLogin());
    }

    @Override
    @Cacheable(cacheNames = "employee", key = "#id")
    public Employee findEmployeeById(BigInteger id) {
        return employeeRepository.findById(id).orElseThrow(IndexOutOfBoundsException::new);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @CacheEvict
    public Employee updateEmployee(BigInteger id, Employee employeeRequest) {
        employeeRepository.findById(id).map(employee -> {
            employee.setId(employeeRequest.getId());
            employee.setName(employeeRequest.getName());
            employee.setEmployeeCategory(employeeRequest.getEmployeeCategory());
            employee.setLogin(employeeRequest.getLogin());
            employee.setPassword(employeeRequest.getPassword());
            return employeeRepository.save(employee);
        });
        return employeeRequest;
    }

    @Override
    public void deleteEmployeeById(BigInteger id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
        } else {
            throw new NotFoundException("User not found with id " + id);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findEmployeeByLogin(login);
        if (employee == null) {
            throw new NotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

        return new User(employee.getName(), employee.getPassword(), authorities);

    }
}
