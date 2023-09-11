package com.shuaiwu.oauth2.service.impl;

import com.shuaiwu.oauth2.entity.OauthClientDetails;
import com.shuaiwu.oauth2.mapper.OauthClientDetailsMapper;
import com.shuaiwu.oauth2.service.IOauthClientDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shuaiwu
 * @since 2023-09-10
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {

}
