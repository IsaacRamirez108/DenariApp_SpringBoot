package com.denarisolutions.denariapp_springboot.repositories;

import com.denarisolutions.denariapp_springboot.models.RentalData;
import com.denarisolutions.denariapp_springboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalDataRepository extends JpaRepository<RentalData, Long> {
    RentalData findById(long id);
}
