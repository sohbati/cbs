package com.sina.cbs.information.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sina.cbs.information.office.domain.Office;

@Repository
public interface OfficeRepository extends JpaRepository<Office, String>{
    
}
