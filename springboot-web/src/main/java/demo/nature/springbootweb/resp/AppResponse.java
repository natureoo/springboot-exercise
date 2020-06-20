package demo.nature.springbootweb.resp;

/**
 * @author nature
 * @date 15/6/2020 10:56 下午
 * @email 924943578@qq.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * APP端返回信息协议实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppResponse {

    private String code;

    private String message;

    private Object data;

    private String signature;



}
