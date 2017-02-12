package com.wxweven.service;

import com.wxweven.model.User;

/**
 * @author wxweven
 * @date 2016年4月19日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog http://wxweven.com/
 * @Copyright: Copyright (c) wxweven 2009-2016
 * @Description:
 */
public interface IUserService {

	/**
	 * 通过userId获取user
	 * @param userId
	 * @return
	 */
	public User getUserById(int userId);
}
