package demo.nature.springbootjava.mapper;

import demo.nature.springbootjava.model.Person;
import demo.nature.springbootjava.model.PersonDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * @author nature
 * @date 12/8/2020 5:31 下午
 * @email 924943578@qq.com
 */
@Component
public class Mapper extends ConfigurableMapper {
    protected void configure(MapperFactory factory) {
        factory.getConverterFactory().registerConverter(new InstantToOptionalConverter());
        factory.classMap(Person.class, PersonDto.class)
                .field("name.first", "firstName")
                .field("name.last", "lastName")
//                .field("address.value", "address")
//                .field("knownAliases{first}", "aliases{[0]}")
//                .field("knownAliases{last}", "aliases{[1]}")
                .byDefault()
                .register();
    }
}
