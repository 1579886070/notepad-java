package xyz.zx21.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.zx21.service.UserService;
import xyz.zx21.utils.ReturnMsgUtil;
import xyz.zx21.vo.UserVo;

/**
 * @author Administrator
 * @date 2020/3/2 14:24
 */
//@CrossOrigin
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public Object list(@RequestParam(value = "start", required = false, defaultValue = "1") int start,
                       @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        ReturnMsgUtil result = userService.getList(start, limit);
        return result;
    }

    @PostMapping("register")
    public Object register(@RequestBody UserVo userVo) {
        ReturnMsgUtil result = userService.register(userVo);
        return result;
    }

    @PostMapping("update")
    public Object update(@RequestBody UserVo userVo) {
        ReturnMsgUtil result = userService.updateUser(userVo);
        return result;
    }

    @GetMapping("byId")
    public Object selectById(@RequestParam("id") int id) {
        ReturnMsgUtil result = userService.selectById(id);
        return result;
    }

    @GetMapping("byName")
    public Object selectByName(@RequestParam("userName") String userName) {
        ReturnMsgUtil result = userService.selectByUserName(userName);
        return result;
    }

    @PostMapping("login")
    public Object login(@RequestBody UserVo userVo){
        ReturnMsgUtil result = userService.login(userVo);
        return result;
    }

    @PostMapping("updateStatus")
    public Object updateStatus(@RequestParam("id") Integer id,@RequestParam("status") Integer status){
        ReturnMsgUtil result = userService.updateStatusById(id,status);
        return result;
    }
}
