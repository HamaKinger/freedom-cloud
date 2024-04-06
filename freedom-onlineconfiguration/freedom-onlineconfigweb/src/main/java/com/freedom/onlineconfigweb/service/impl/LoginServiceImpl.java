package com.freedom.onlineconfigweb.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.freedom.common.enums.ResultCodeEnum;
import com.freedom.common.vo.Result;
import com.freedom.common.vo.ResultUtils;
import com.freedom.onlineconfigweb.mapper.LoginMapper;
import com.freedom.onlineconfigweb.po.User;
import com.freedom.onlineconfigweb.service.LoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author freedom
 * @description
 * @date 2024/4/5 9:26
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;
    @Override
    public Result<String> sign (String username,String password,boolean remember) {
        try {
            User user = loginMapper.selectOne(new QueryWrapper<User>().eq("username",username));
            String pass = user.getPassword();
            String s = SaSecureUtil.aesDecrypt("6ay4dlwd4enjbf90",pass);
            if(s.equals(password)){
                StpUtil.login(username,remember);
                return ResultUtils.buildResult(ResultCodeEnum.SUCCESS);
            }else{
                return ResultUtils.buildResult(ResultCodeEnum.FAILD,"密码错误!");
            }
        }catch(Exception e) {
            log.error("校验失败!",e);
        }
        return ResultUtils.buildResult(ResultCodeEnum.UNKNO,"登录失败");
    }

    public static void main (String[] args) {
        String s = SaSecureUtil.aesEncrypt("6ay4dlwd4enjbf90","onlineconfigweb");
        System.out.println("s = " + s);
        String s1 = SaSecureUtil.aesDecrypt("6ay4dlwd4enjbf90",s);
        System.out.println("s1 = " + s1);

    }
}
