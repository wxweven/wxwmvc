package com.wxweven.view;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

/**
 * @author wxweven
 * @version 1.0
 * @date 2016年4月18日
 * @email wxweven@qq.com
 * @blog http://wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2016
 * @Description:
 */
@Component
public class HelloView implements View {

	public String getContentType() {
		return "text/html";
	}

	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.getWriter().print("hello view, time: " + new Date());
	}

}
