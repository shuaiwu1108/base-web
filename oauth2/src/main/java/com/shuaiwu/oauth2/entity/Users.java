package com.shuaiwu.oauth2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author shuaiwu
 * @since 2023-09-12
 */
@Getter
@Setter
@TableName("users")
@Schema(title = "用户信息")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "用户名")
    @TableId("username")
    private String username;

    @Schema(title = "密码")
    @TableField("password")
    private String password;

    @Schema(title = "是否有效")
    @TableField("enabled")
    private Boolean enabled;
}
