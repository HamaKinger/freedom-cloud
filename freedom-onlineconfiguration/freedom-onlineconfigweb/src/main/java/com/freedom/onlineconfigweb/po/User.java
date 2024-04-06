package com.freedom.onlineconfigweb.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author freedom
 * @description
 * @date 2024/4/5 10:01
 */
@Data
@TableName(value = "free_user")
public class User {
    @TableId
    private String id ;
    private String username ;
    private String password ;
    private String name ;
    private String age ;
}
