package com.zeus.app.ws.io.repository;

import com.zeus.app.ws.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> { // <class of obj that needs to be persisted, type of DB id field>
	UserEntity findByEmail(String email);
}
