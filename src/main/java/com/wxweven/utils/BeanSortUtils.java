package com.wxweven.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;

/**
 * @author wxweven
 * @version 1.0
 * @date 2016年4月19日
 * @email wxweven@qq.com
 * @website http://wxweven.com/
 * @Copyright: Copyright (c) wxweven 2016
 * @Description: JavaBean 按照字段排序
 */
public class BeanSortUtils {

	/**
	 * 将javabean list按照指定的字段排序
	 * 
	 * @param list
	 *            封装javabean的list
	 * @param orderField
	 *            排序字段
	 * @param orderDirection
	 *            排序方向
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> sort(List<T> list, String orderField, String orderDirection) {
		// 创建针对某个属性的升序比较
		Comparator<T> comparator = new BeanComparator<T>(orderField, new NullComparator(false));

		if ("desc".equalsIgnoreCase(orderDirection)) {
			// desc :降序排序
			comparator = new ReverseComparator(comparator);
		}

		// 开始排序
		Collections.sort(list, comparator);
		
		return list;
	}

	/**
	 * 将javabean list按照指定的多个字段排序
	 * 
	 * @param list
	 *            封装javabean的list
	 * @param orderMap
	 *            封装排序字段以及方向的map排序字段
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> sortMutil(List<T> list, Map<String, Boolean> orderMap) {
		// 创建多属性排序链
		ComparatorChain cc = new ComparatorChain();

		for (String key : orderMap.keySet()) {
			// 第一个参数是指定排序的属性，第二个参数指定降序升序,true:降序，false:升序
			cc.addComparator(new BeanComparator<T>(key), orderMap.get(key));
		}

		Collections.sort(list, cc);
		
		return list;
	}
}