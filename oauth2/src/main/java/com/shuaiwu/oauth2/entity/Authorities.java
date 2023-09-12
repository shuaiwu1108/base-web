package com.shuaiwu.oauth2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("authorities")
@Schema(title = "权限信息")
public class Authorities implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "用户名")
    @TableField("username")
    private String username;

    @Schema(title = "用户角色")
    @TableField("authority")
    private String authority;
}
