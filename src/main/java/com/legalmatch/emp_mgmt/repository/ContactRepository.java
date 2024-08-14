package com.legalmatch.emp_mgmt.repository;

import com.legalmatch.emp_mgmt.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
