package com.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import com.app.dao.models.User;
import com.app.dto.CustomerSearchRequest;

@SpringBootTest
class TestCriteriaQuery {
	@Autowired
	private EntityManager mgr;

	@Test
	void test() {
		assertNotNull(mgr);
		CustomerSearchRequest request = new CustomerSearchRequest();
		request.setName("Ayushi");
		request.setEmail("ayu@gmail.com");
		request.setDob(LocalDate.of(1995, 3, 17));
		
		CriteriaBuilder criteriaBuilder = mgr.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		
		List<Predicate> searchCriterias = new ArrayList<>();
		if (StringUtils.hasLength(request.getName()))
			searchCriterias.add(criteriaBuilder.equal(root.get("name"), request.getName()));
		criteriaQuery.select(root)
				.where(criteriaBuilder.and(searchCriterias.toArray(new Predicate[searchCriterias.size()])));
		 assertEquals(2,mgr.createQuery(criteriaQuery).getSingleResult().getCustId());
		 
	}

}
