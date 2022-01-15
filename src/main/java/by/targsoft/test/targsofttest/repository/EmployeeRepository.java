package by.targsoft.test.targsofttest.repository;

import by.targsoft.test.targsofttest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, BigInteger> {
    Employee findEmployeeByLogin(String login);
}
