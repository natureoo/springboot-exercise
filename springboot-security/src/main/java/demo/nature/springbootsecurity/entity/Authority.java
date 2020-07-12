package demo.nature.springbootsecurity.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author nature
 * @date 12/7/2020 1:08 下午
 * @email 924943578@qq.com
 */
@Entity
@Data
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

}
