package com.access.sys.mapper;

import com.access.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Free
 * @since 2023-02-25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<String> getRoleNameByUserId(Integer userId);
}
