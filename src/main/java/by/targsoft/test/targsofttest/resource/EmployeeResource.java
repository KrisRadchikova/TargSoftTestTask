package by.targsoft.test.targsofttest.resource;

import by.targsoft.test.targsofttest.dto.EmployeeDto;
import by.targsoft.test.targsofttest.entity.Employee;
import by.targsoft.test.targsofttest.service.interfaces.EmployeeService;
import by.targsoft.test.targsofttest.service.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {
    private final EmployeeService employeeService;
    private final EmployeeMapper mapper;

    @Autowired
    public EmployeeResource(EmployeeService employeeService, EmployeeMapper mapper) {
        this.employeeService = employeeService;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = mapper.toEntity(employeeDto);
        return mapper.toDto(employeeService.createEmployee(employee));
    }

    @GetMapping("/findEmployee/{id}")
    public EmployeeDto findEmployeeById(@PathVariable BigInteger id) {
        return mapper.toDto(employeeService.findEmployeeById(id));
    }

    @GetMapping("/getAll")
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/update/{id}")
    public EmployeeDto updateEmployee(@PathVariable BigInteger id, @RequestBody EmployeeDto employeeDto) {
        Employee employee = mapper.toEntityForUpdate(id, employeeDto);
        return mapper.toDto(employeeService.updateEmployee(id, employee));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable BigInteger id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().build();
    }
}
