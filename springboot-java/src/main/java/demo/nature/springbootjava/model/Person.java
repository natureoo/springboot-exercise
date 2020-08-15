package demo.nature.springbootjava.model;

import java.util.Optional;

/**
 * @author nature
 * @date 12/8/2020 5:12 下午
 * @email 924943578@qq.com
 */
public class Person {
    private Name name;
    private Optional<String> address;

    public Person(){

    }

    public Person(Name name) {
        this.name = name;

    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<String> getAddress() {
        return address;
    }

    public void setAddress(Optional<String> address) {
        this.address = address;
    }
}


