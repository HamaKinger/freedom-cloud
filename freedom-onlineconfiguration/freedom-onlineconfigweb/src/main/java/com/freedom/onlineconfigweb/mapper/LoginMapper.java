package com.freedom.onlineconfigweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.freedom.onlineconfigweb.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author freedom
 * @description
 * @date 2024/4/5 9:24
 */
@Mapper
public interface LoginMapper extends BaseMapper<User> {

}
