package demo.nature.springbootsignatureserver.data.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author nature
 * @date 25/7/2020 11:12 下午
 * @email 924943578@qq.com
 */
@Getter
@Setter
@NoArgsConstructor
public class RequestHeader {

    private String clientId;

    private String msgId;

    private LocalDateTime requestTime;
}
