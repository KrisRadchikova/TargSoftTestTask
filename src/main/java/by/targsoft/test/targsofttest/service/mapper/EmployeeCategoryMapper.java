package by.targsoft.test.targsofttest.service.mapper;

import by.targsoft.test.targsofttest.dto.EmployeeCategoryDto;
import by.targsoft.test.targsofttest.entity.EmployeeCategory;
import by.targsoft.test.targsofttest.service.interfaces.Mapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCategoryMapper implements Mapper<EmployeeCategory, EmployeeCategoryDto> {

    @Override
    public EmployeeCategoryDto toDto(EmployeeCategory entity) {
        EmployeeCategoryDto dto = new EmployeeCategoryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    @Override
    public EmployeeCategory toEntity(EmployeeCategoryDto dto) {
        EmployeeCategory employeeCategory = new EmployeeCategory();
        employeeCategory.setId(dto.getId());
        employeeCategory.setName(dto.getName());
        return employeeCategory;
    }
}
