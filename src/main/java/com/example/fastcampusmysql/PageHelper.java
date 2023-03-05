package com.example.fastcampusmysql;

import java.util.List;

import org.springframework.data.domain.Sort;

public class PageHelper {
	public static String orderBy(Sort sort) {
		if (sort.isEmpty()) {
			return "id DESC";
		}

		List<Sort.Order> orders = sort.toList();
		var orderBys = orders.stream()
			.map(order -> order.getProperty() + " " + order.getDirection())
			.toList();

		System.out.println(orderBys);

		return String.join(", ", orderBys);
	}
}
