package by.targsoft.test.targsofttest.repository;

import by.targsoft.test.targsofttest.entity.EmployeeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface EmployeeCategoryRepository extends JpaRepository<EmployeeCategory, BigInteger> {
    EmployeeCategory findCategoryByName(String name);
}
