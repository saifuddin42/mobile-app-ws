package com.zeus.app.ws.io.repository;

import com.zeus.app.ws.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> { // <class of obj that needs to be persisted, type of DB id field>
	// naming convention is findBy (spring default for select in db) plus the column/attribute in the table in db (you can ignore case and underscores in the column name)
	UserEntity findByEmail(String email);
	UserEntity findByUserID(String userID);
}
