package demo.nature.springbootjava;

import demo.nature.springbootjava.model.Name;
import demo.nature.springbootjava.model.Person;
import demo.nature.springbootjava.model.PersonDto;
import ma.glasnost.orika.MapperFacade;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;


@SpringBootTest
class SpringbootJavaApplicationTests {

	@Resource
	protected MapperFacade mapperFacade;

	@Test
	void testOrikaMapper() {
//		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
//		MapperFacade mapperFacade = mapperFactory.getMapperFacade();

		Name name = new Name("zhang", "sanfeng");
		Person person = new Person();
		person.setName(name);
		person.setAddress(Optional.ofNullable("zhongshandadao"));
		PersonDto personDto = mapperFacade.map(person, PersonDto.class);
		System.out.println(personDto);
	}

	@Test
	void testReflection() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Name name = new Name("zhang", "sanfeng");
		Class<?>[] parameterTypes = null;
		Object[] objs = null;
//		Class<?>[] parameterTypes = new Class<?>[]{};
//		Object[] objs = new Object[]{};
		Method method = name.getClass().getDeclaredMethod("getFirst", parameterTypes);
		Object obj = method.invoke(name, objs);
		System.out.println("obj:"+obj);
	}

	@Test
	void testSomeThing() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
//		Object[] obj = new Object[]{"AAA", null};
//		System.out.println("obj:"+obj);

//		Optional<LocalDate> optionalLocalDate = Optional.empty();
//
//		Instant instant = Instant.now();
//		Class<?> clazz = Class.forName("java.util.Optional");
//		Object obj = mapperFacade.map(instant, clazz);
//		System.out.println("optional:"+obj);
//		System.out.println("clazz1:"+obj.getClass());
//		System.out.println("clazz2:"+optionalLocalDate.getClass());

		String name1 = Optional.of("baeldung").orElse("Other");
		System.out.println("name:"+name1);



	}

}
