package by.targsoft.test.targsofttest.service.interfaces;

import by.targsoft.test.targsofttest.entity.EmployeeCategory;

import java.math.BigInteger;
import java.util.List;

public interface EmployeeCategoryService {

    EmployeeCategory createCategory(EmployeeCategory employeeCategory);

    EmployeeCategory findCategoryById(BigInteger id);

    EmployeeCategory findCategoryByName(String name);

    List<EmployeeCategory> getAllCategories();

    EmployeeCategory updateCategory(BigInteger id, EmployeeCategory employeeCategory);

    void deleteCategory(BigInteger id);
}
