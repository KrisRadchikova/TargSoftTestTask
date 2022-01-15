package by.targsoft.test.targsofttest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {

    BigInteger id;
    String name;
    BigInteger category_id;
    String login;
    String password;

}
