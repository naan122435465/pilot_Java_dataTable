package com.training.dao.jpaspec;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import com.training.entity.OrdersEntity;

public class OrderJpaSpecitification {
	/**
	 * Get Search criteria for query to search orders
	 * 
	 * @param searchConditionsMap
	 * @return
	 */
	public static Specification<OrdersEntity> getSearchCriteria(Map<String, Object> searchConditionsMap) {
		return new Specification<OrdersEntity>() {

			@Override
			public Predicate toPredicate(Root<OrdersEntity> orderRoot, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList();
				if (searchConditionsMap != null) {
					String keyword = (String) searchConditionsMap.get("keyword");
					String dateFrom = (String) searchConditionsMap.get("dateFrom");
					String dateTo = (String) searchConditionsMap.get("dateTo");
					// Join<OrderDetailsEntity, OrdersEntity> orderDetailsRoot =
					// orderRoot.join("orderDetailsEntity");
					// Keyword Predicate
					if (StringUtils.isNotEmpty(keyword)) {
						predicates.add(criteriaBuilder.or(
								criteriaBuilder.like(orderRoot.get("customerAdress"), "%" + keyword + "%"),
								criteriaBuilder.like(orderRoot.get("customerEmail"), "%" + keyword + "%"),
								criteriaBuilder.like(orderRoot.get("customerName"), "%" + keyword + "%"),
								criteriaBuilder.like(orderRoot.get("customerPhone"), "%" + keyword + "%")));
					}
					// Date From Predicate
					if (StringUtils.isNotEmpty(dateFrom)) {
						predicates.add(
								criteriaBuilder.greaterThanOrEqualTo(orderRoot.get("orderDate"), Date.parse(dateFrom)));
					}
					if (StringUtils.isNotEmpty(dateTo)) {
						predicates
								.add(criteriaBuilder.lessThanOrEqualTo(orderRoot.get("orderDate"), Date.parse(dateTo)));
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}

		};

	}

}
