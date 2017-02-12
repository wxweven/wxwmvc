package com.wxweven.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxweven.mapper.UserMapper;
import com.wxweven.model.User;
import com.wxweven.service.IUserService;

/**
 * @author wxweven
 * @date 2016年4月19日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog http://wxweven.com/
 * @Copyright: Copyright (c) wxweven 2009-2016
 * @Description:
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Resource
	private UserMapper userMapper;

	public User getUserById(int userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		
		return user;
	}
	
	
	
	

}
