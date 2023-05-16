package com.denarisolutions.denariapp_springboot.repositories;

import com.denarisolutions.denariapp_springboot.models.PersonalInfo;
import com.denarisolutions.denariapp_springboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {
    PersonalInfo findById(long id);
}
