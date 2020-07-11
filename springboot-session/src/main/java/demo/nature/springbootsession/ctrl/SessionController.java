package demo.nature.springbootsession.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author nature
 * @date 10/7/2020 7:03 下午
 * @email 924943578@qq.com
 */
@RestController
@RequestMapping(value = "session")
public class SessionController {

    @RequestMapping(value = "set")
    public String set(HttpServletRequest request){
        request.getSession().setAttribute("url", "www.baidu.com");
        return "OK";
    }

    @RequestMapping(value = "get")
    public String get(HttpServletRequest request){
        return (String) request.getSession().getAttribute("url");
    }
}
