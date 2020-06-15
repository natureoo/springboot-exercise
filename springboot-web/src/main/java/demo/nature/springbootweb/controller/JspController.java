package demo.nature.springbootweb.controller;

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

    @GetMapping("config")
    @ResponseBody
    public String config(){
        return "config";
    }

    @GetMapping("login")
    @ResponseBody
    public String login(){
        System.out.println("in login");
        return "login";
    }

    @GetMapping("uuid/{index}")
    @ResponseBody
    public String uuid(@PathVariable("index") int index){
        return uuidService.randUUID(index) ;
    }

    @PostMapping(value = "updateDog")
//    AppResponse updateDog(@Valid @RequestBody Dog dog, Errors errors){
    AppResponse updateDog(@Valid @RequestBody Dog dog){
//        System.out.println(errors);
        AppResponse resp = new AppResponse();

        // 执行业务
        Dog newDog = dogService.update(dog);

        // 返回数据
        resp.setCode("0000");
        resp.setMessage("updateDog succ!");

        return resp;
    }
}
