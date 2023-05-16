package com.denarisolutions.denariapp_springboot.repositories;

import com.denarisolutions.denariapp_springboot.models.Address;
import com.denarisolutions.denariapp_springboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findById(long id);
}
