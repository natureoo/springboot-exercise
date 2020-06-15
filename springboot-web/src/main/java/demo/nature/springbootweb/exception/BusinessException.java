package demo.nature.springbootweb.exception;

/**
 * @author nature
 * @date 15/6/2020 10:16 下午
 * @email 924943578@qq.com
 */
public class BusinessException extends RuntimeException {

    public BusinessException(){
        super();
    }

    public BusinessException(String message){
        super(message);
    }
}