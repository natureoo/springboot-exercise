package demo.nature.springbootsignatureserver.data.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nature
 * @date 25/7/2020 11:11 下午
 * @email 924943578@qq.com
 */
@Getter
@Setter
@NoArgsConstructor
public class RequestPayload {

    private RequestPayload header;

    private RequestData data;
}
