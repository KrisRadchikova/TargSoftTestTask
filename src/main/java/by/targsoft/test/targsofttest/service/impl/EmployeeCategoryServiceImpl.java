package by.targsoft.test.targsofttest.service.impl;

import by.targsoft.test.targsofttest.entity.EmployeeCategory;
import by.targsoft.test.targsofttest.exception.NotFoundException;
import by.targsoft.test.targsofttest.repository.EmployeeCategoryRepository;
import by.targsoft.test.targsofttest.service.interfaces.EmployeeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.math.BigInteger;
import java.util.List;

@Service
public class EmployeeCategoryServiceImpl implements EmployeeCategoryService {

    private final EmployeeCategoryRepository employeeCategoryRepository;

    @Autowired
    public EmployeeCategoryServiceImpl(EmployeeCategoryRepository employeeCategoryRepository) {
        this.employeeCategoryRepository = employeeCategoryRepository;
    }

    @Override
    public EmployeeCategory createCategory(EmployeeCategory employeeCategory) {
        if (employeeCategoryRepository.findCategoryByName(employeeCategory.getName()) != null) {
            throw new EntityExistsException("Category exists");
        }
        return employeeCategoryRepository.save(employeeCategory);
    }

    @Override
    public EmployeeCategory findCategoryById(BigInteger id) {
        return employeeCategoryRepository.findById(id).orElseThrow(IndexOutOfBoundsException::new);
    }

    @Override
    public EmployeeCategory findCategoryByName(String name) {
        return employeeCategoryRepository.findCategoryByName(name);
    }

    @Override
    public List<EmployeeCategory> getAllCategories() {
        return employeeCategoryRepository.findAll();
    }

    @Override
    public EmployeeCategory updateCategory(BigInteger id, EmployeeCategory employeeCategoryRequest) {
        employeeCategoryRepository.findById(id).map(category -> {
            category.setName(employeeCategoryRequest.getName());
            return employeeCategoryRepository.save(category);
        });
        return employeeCategoryRequest;
    }

    @Override
    public void deleteCategory(BigInteger id) {
        if (employeeCategoryRepository.findById(id).isPresent()) {
            employeeCategoryRepository.deleteById(id);
        } else {
            throw new NotFoundException("Employee category not found with id " + id);
        }
    }
}
