package demo.nature.springbootweb.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author nature
 * @date 15/6/2020 11:00 下午
 * @email 924943578@qq.com
 */
@Data
public class Dog {

    @NotNull(message = "{Dog.id.non}")
    @Min(value = 1, message = "{Dog.id.lt1}")
    private Long id;

    @NotBlank(message = "{Dog.name.non}")
    private String name;

    @Min(value = 1, message = "{Dog.age.lt1}")
    private Integer age;

}
