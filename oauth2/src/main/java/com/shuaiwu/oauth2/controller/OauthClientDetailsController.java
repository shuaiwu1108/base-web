package com.shuaiwu.oauth2.controller;

import com.shuaiwu.oauth2.entity.OauthClientDetails;
import com.shuaiwu.oauth2.service.IOauthClientDetailsService;
import com.shuaiwu.oauth2.utils.SecretUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shuaiwu
 * @since 2023-09-10
 */

@Tag(name = "OAuth秘钥的生成")
@RestController
@RequestMapping("oauth")
public class OauthClientDetailsController {

    @Autowired
    private IOauthClientDetailsService clientDetailsService;

    /**
     * 生成一对clientid、clientsecret
     * 通过time、timeRefresh请求参数，分别设置accesstoken、refreshtoken的过期时间
     * @param params
     * @return
     */
    @Operation(summary = "注册")
    @Parameters(value = {
        @Parameter(name = "time", description = "AccessToken的超时时间", example = "7200"),
        @Parameter(name = "timeRefresh", description = "RefreshToken的超时时间", example = "7200")
    })
    @RequestMapping("register")
    public Object registerClient(@RequestBody Map<String, Integer> params){
        OauthClientDetails clientDetails = new OauthClientDetails();
        clientDetails.setClientId(SecretUtil.generateClientId());
        String tmpSecret = SecretUtil.generateCliendSecret();
        clientDetails.setClientSecret(SecretUtil.encode(tmpSecret));
        clientDetails.setAuthorizedGrantTypes("client_credentials,refresh_token");
        clientDetails.setScope("read,write");
        clientDetails.setAutoapprove("false");
        clientDetails.setAccessTokenValidity(params.get("time"));
        clientDetails.setRefreshTokenValidity(params.get("timeRefresh"));
        clientDetailsService.save(clientDetails);
        Map<String, String> res = new HashMap<>();
            res.put("cliend_id", clientDetails.getClientId());
            res.put("client_secret", tmpSecret);
            res.put("grant_types", "client_credentials, refresh_token");
            res.put("scope", "read, write");
        return res;
    }
}
