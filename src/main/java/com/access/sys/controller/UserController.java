package com.access.sys.controller;

import com.access.common.vo.ResponseEntity;
import com.access.sys.entity.User;
import com.access.sys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @GetMapping("/list")
    public ResponseEntity<Map<String,Object>> getUserList(@RequestParam(value = "username",required = false) String username,
                                                      @RequestParam(value = "phone",required = false) String phone,
                                                      @RequestParam(value = "pageNo") Long pageNo,
                                                      @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper();
        //条件1
        wrapper.eq(StringUtils.hasLength(username),User::getUsername,username);
        //条件2
        wrapper.eq(StringUtils.hasLength(phone),User::getPhone,phone);
        Page<User> page = new Page<>(pageNo,pageSize);
        userService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return ResponseEntity.success(data);
    }
}
