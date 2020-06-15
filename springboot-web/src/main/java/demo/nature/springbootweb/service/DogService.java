package demo.nature.springbootweb.service;

import demo.nature.springbootweb.exception.BusinessException;
import demo.nature.springbootweb.model.Dog;
import org.springframework.stereotype.Service;

/**
 * @author nature
 * @date 15/6/2020 11:13 下午
 * @email 924943578@qq.com
 */
@Service
public class DogService {


    public Dog update(Dog dog){

        // some database options

        // 模拟狗狗新名字与其他狗狗的名字冲突
        throw new BusinessException("ControllerAdvice exception test");

        // update database dog info

        // query dog info from database after update

    }

}
