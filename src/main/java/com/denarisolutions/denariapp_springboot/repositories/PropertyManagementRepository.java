package com.denarisolutions.denariapp_springboot.repositories;

import com.denarisolutions.denariapp_springboot.models.PropertyManagement;
import com.denarisolutions.denariapp_springboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyManagementRepository extends JpaRepository<PropertyManagement, Long> {
    PropertyManagement findById(long id);

}
