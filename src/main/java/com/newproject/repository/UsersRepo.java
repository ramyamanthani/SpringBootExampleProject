package com.newproject.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.newproject.entity.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, String>{

	@Query("select u from Users u where u.userId=:userId")
    Users findByUserId(@Param("userId") String userId);
	
	@Query(value = "select u.user_name from Users u where u.user_name=:userName", nativeQuery = true)
	String findByUserName(@Param("userName") String userName);
	
	//@Query(value = "select u.user_id,u.user_name, u.date,u.email,u.date_of_birth,u.first_name,u.last_name,u.phone_number,u.password from Users u where u.date between :startDate and :endDate", nativeQuery = true)
	@Query("select u from Users u where u.date between :startDate and :endDate")
	List<Users> findUsersBetweenRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
