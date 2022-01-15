package by.targsoft.test.targsofttest.resource;

import by.targsoft.test.targsofttest.dto.EmployeeCategoryDto;
import by.targsoft.test.targsofttest.entity.EmployeeCategory;
import by.targsoft.test.targsofttest.service.interfaces.EmployeeCategoryService;
import by.targsoft.test.targsofttest.service.mapper.EmployeeCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class EmployeeCategoryResource {

    private final EmployeeCategoryService employeeCategoryService;
    private final EmployeeCategoryMapper mapper;

    @Autowired
    public EmployeeCategoryResource(EmployeeCategoryService employeeCategoryService, EmployeeCategoryMapper mapper) {
        this.employeeCategoryService = employeeCategoryService;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public EmployeeCategoryDto createCategory(@RequestBody EmployeeCategoryDto employeeCategoryDto) {
        EmployeeCategory employeeCategory = mapper.toEntity(employeeCategoryDto);
        return mapper.toDto(employeeCategoryService.createCategory(employeeCategory));
    }

    @GetMapping("/findCategory/{id}")
    public EmployeeCategoryDto findCategoryById(@PathVariable BigInteger id) {
        return mapper.toDto(employeeCategoryService.findCategoryById(id));
    }

    @GetMapping("/getAll")
    public List<EmployeeCategoryDto> getAllCategories() {
        return employeeCategoryService.getAllCategories().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/update/{id}")
    public EmployeeCategoryDto updateCategory(@PathVariable BigInteger id, @RequestBody EmployeeCategoryDto employeeCategoryDto) {
        EmployeeCategory employeeCategory = mapper.toEntity(employeeCategoryDto);
        return mapper.toDto(employeeCategoryService.updateCategory(id, employeeCategory));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable BigInteger id) {
        employeeCategoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
