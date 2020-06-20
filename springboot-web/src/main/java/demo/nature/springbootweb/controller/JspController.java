package demo.nature.springbootweb.controller;

import com.alibaba.fastjson.JSONObject;
import demo.nature.springbootweb.model.Dog;
import demo.nature.springbootweb.resp.AppResponse;
import demo.nature.springbootweb.service.DogService;
import demo.nature.springbootweb.service.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author nature
 * @date 12/6/2020 11:32 下午
 * @email 924943578@qq.com
 */
@Controller
@RequestMapping("jsp")
public class JspController {

    @Autowired
    private UUIDService uuidService;

    @Autowired
    private DogService dogService;

    @GetMapping("index")
    public String index(Model model){
        model.addAttribute("name", "李四");
        return "index";
    }

    @GetMapping(value="config", produces="application/json")
    @ResponseBody
    public String config(){
        return "config";
    }

    @GetMapping(value = "login", produces="application/json")
    @ResponseBody
    public String login(){
        System.out.println("in login");
        return "login";
    }

    /**
     * consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
     * produces: 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
     * 仅当设置produces="application/json"时MappingJackson2HttpMessageConverter才生效
     * @param index
     * @return
     */
    @GetMapping(value = "uuid/{index}", produces="application/json")
    @ResponseBody
    public String uuid(@PathVariable("index") int index){
        return uuidService.randUUID(index) ;
    }

    @PostMapping(value = "updateDog", produces="application/json")
    @ResponseBody
    AppResponse updateDog(@Valid @RequestBody Dog dog){
        System.out.println(JSONObject.toJSON(dog));
        AppResponse resp = new AppResponse();

        // 执行业务
        Dog newDog = dogService.update(dog);

        // 返回数据
        resp.setCode("0000");
        resp.setMessage("updateDog succ!");

        return resp;
    }
}
