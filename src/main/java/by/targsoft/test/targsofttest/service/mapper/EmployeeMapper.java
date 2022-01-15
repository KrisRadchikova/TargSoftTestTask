package by.targsoft.test.targsofttest.service.mapper;

import by.targsoft.test.targsofttest.dto.EmployeeDto;
import by.targsoft.test.targsofttest.entity.Employee;
import by.targsoft.test.targsofttest.entity.EmployeeCategory;
import by.targsoft.test.targsofttest.service.interfaces.EmployeeCategoryService;
import by.targsoft.test.targsofttest.service.interfaces.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class EmployeeMapper implements Mapper<Employee, EmployeeDto> {

    private final EmployeeCategoryService employeeCategoryService;

    @Autowired
    public EmployeeMapper(EmployeeCategoryService employeeCategoryService) {
        this.employeeCategoryService = employeeCategoryService;
    }

    @Override
    public EmployeeDto toDto(Employee entity) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCategory_id(entity.getEmployeeCategory().getId());
        dto.setLogin(entity.getLogin());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    @Override
    public Employee toEntity(EmployeeDto dto) {
        EmployeeCategory employeeCategory = null;
        if (dto.getCategory_id() != null) {
            employeeCategory = employeeCategoryService.findCategoryById(dto.getCategory_id());
        }
        Employee entity = new Employee();
        entity.setName(dto.getName());
        entity.setEmployeeCategory(employeeCategory);
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public Employee toEntityForUpdate(BigInteger id, EmployeeDto dto) {
        EmployeeCategory employeeCategory = null;
        if (dto.getCategory_id() != null) {
            employeeCategory = employeeCategoryService.findCategoryById(dto.getCategory_id());
        }
        Employee entity = new Employee();
        entity.setId(id);
        entity.setName(dto.getName());
        entity.setEmployeeCategory(employeeCategory);
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        return entity;
    }
}
