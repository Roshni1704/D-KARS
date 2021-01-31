package com.app.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dao.models.CustomerCardDetails;

public interface CustomerCardDetailsRespoitory extends JpaRepository<CustomerCardDetails, Integer> {

}
