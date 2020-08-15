package demo.nature.springbootjava.model;

import java.util.Date;

/**
 * @author nature
 * @date 12/8/2020 5:13 下午
 * @email 924943578@qq.com
 */
public class PersonDto {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String address;

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Date getBirthDate() {
        return birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
