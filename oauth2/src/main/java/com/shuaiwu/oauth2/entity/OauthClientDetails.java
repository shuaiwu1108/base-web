package com.shuaiwu.oauth2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author shuaiwu
 * @since 2023-09-10
 */
@Data
@TableName("oauth_client_details")
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;
    @Override
    public String toString() {
        return "OauthClientDetails{" +
            "clientId = " + clientId +
            ", resourceIds = " + resourceIds +
            ", clientSecret = " + clientSecret +
            ", scope = " + scope +
            ", authorizedGrantTypes = " + authorizedGrantTypes +
            ", webServerRedirectUri = " + webServerRedirectUri +
            ", authorities = " + authorities +
            ", accessTokenValidity = " + accessTokenValidity +
            ", refreshTokenValidity = " + refreshTokenValidity +
            ", additionalInformation = " + additionalInformation +
            ", autoapprove = " + autoapprove +
        "}";
    }
}
