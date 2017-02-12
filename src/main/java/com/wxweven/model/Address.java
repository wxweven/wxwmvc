package com.wxweven.model;

/**
 * @author wxweven
 * @version 1.0
 * @date 2016年4月18日
 * @email wxweven@qq.com
 * @blog http://wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2016
 * @Description:
 */
public class Address {

	private String province;
	private String city;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [province=" + province + ", city=" + city + "]";
	}
	
}
