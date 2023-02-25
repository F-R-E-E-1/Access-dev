package com.access;

import com.access.sys.entity.User;
import com.access.sys.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AccessDevApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testMapper() {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

}
