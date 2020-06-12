package demo.nature.springboothelloworld.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author nature
 * @date 11/6/2020 7:06 下午
 * @email 924943578@qq.com
 */
@ConfigurationProperties(prefix = "pay")
@Component
@Data
public class PayConfig {

    private String url;

    private String interval;
}
