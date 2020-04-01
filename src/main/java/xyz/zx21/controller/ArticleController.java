package xyz.zx21.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.zx21.service.ArticleService;
import xyz.zx21.utils.ReturnMsgUtil;
import xyz.zx21.vo.ArticleVo;

/**
 * @author Administrator
 * @date 2020/3/5 10:20
 */
//@CrossOrigin
@RequestMapping("article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;
//    @CrossOrigin
    @GetMapping("list")
    public Object list(@RequestParam(value = "start", required = false, defaultValue = "1") int start,
                       @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        ReturnMsgUtil result = articleService.getList(start, limit);
        return result;
    }

    @PostMapping("push")
    public Object push(@RequestBody ArticleVo articleVo) {
        ReturnMsgUtil result = articleService.insert(articleVo);
        return result;
    }

    @GetMapping("del")
    public Object del(@RequestParam("id") Integer id) {
        ReturnMsgUtil result = articleService.deleteById(id);
        return result;
    }

    @PostMapping("update")
    public Object update(@RequestBody ArticleVo articleVo) {
        ReturnMsgUtil result = articleService.updateById(articleVo);
        return result;
    }

    @GetMapping("byId")
    public Object byId(@RequestParam("id") Integer id) {
        ReturnMsgUtil result = articleService.selectById(id);
        return result;
    }

    @PostMapping("delStatus")
    public Object updateStatus(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        ReturnMsgUtil result = articleService.updateStatusById(id, status);
        return result;
    }

    @GetMapping("byUserName")
    public Object selectByUserName(@RequestParam(value = "start", required = false, defaultValue = "1") int start,
                                 @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                                 @RequestParam(value = "userName", required = true) Integer userName) {
        ReturnMsgUtil result = articleService.getListByUserName(start, limit, userName);
        return result;
    }
}
