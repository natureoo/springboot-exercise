package demo.nature.data.request;

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
public class RequestPayload<T> {

    private RequestHeader header;

    private T data;
}
