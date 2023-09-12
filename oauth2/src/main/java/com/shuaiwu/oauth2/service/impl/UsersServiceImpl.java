package com.shuaiwu.oauth2.service.impl;

import com.shuaiwu.oauth2.entity.Users;
import com.shuaiwu.oauth2.mapper.UsersMapper;
import com.shuaiwu.oauth2.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shuaiwu
 * @since 2023-09-12
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
