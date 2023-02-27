package com.access.sys.controller;

import com.access.common.vo.ResponseEntity;
import com.access.sys.entity.User;
import com.access.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Free
 * @since 2023-02-25
 */
@RestController
@RequestMapping("/user")
//@CrossOrigin  解决跨域
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> userList = userService.list();
        return ResponseEntity.success(userList,"成功");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody User user){
        Map<String,Object> data = userService.login(user);
        if (data != null){
            return ResponseEntity.success(data);
        }
        return ResponseEntity.fail(20002,"用户名或密码错误");
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@RequestParam("token") String token){
        // 根据token获取用户信息,从redis里面
        Map<String,Object> data = userService.getUserInfo(token);
        if (data != null){
            return ResponseEntity.success(data);
        }
        return ResponseEntity.fail(20003,"登录信息无效，请重新登录");
    }

    @PostMapping("/loginout")
    public ResponseEntity<?> loginOut(@RequestHeader("X-token") String token){
        userService.loginOut(token);
        return ResponseEntity.success();
    }
}
