package demo.nature.springbootjpa.ctrl;

import demo.nature.springbootjpa.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nature
 * @date 27/6/2020 12:14 下午
 * @email 924943578@qq.com
 */
@Slf4j
@RestController
@RequestMapping("jpa")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("comment")
    public String comment(Long articleId, String content) {
        try {
            commentService.postComment(articleId, content);
        } catch (Exception e) {
            log.error("{}", e);
            return "error: " + e.getMessage();
        }
        return "success";
    }
}
