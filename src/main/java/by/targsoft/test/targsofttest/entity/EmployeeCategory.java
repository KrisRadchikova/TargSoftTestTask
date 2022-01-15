package by.targsoft.test.targsofttest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmployeeCategory implements Serializable {

    @Id
    @Column(columnDefinition = "numeric(19,0)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @NotEmpty
    @NotBlank
    private String name;

    @OneToMany()
    @JoinColumn(name = "employee_category_id", referencedColumnName = "id")
    private List<Employee> employees;
}
