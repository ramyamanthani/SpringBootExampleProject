package com.newproject.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.newproject.entity.Users;
import com.newproject.exceptions.AlreadyExistsException;
import com.newproject.exceptions.ResourceNotFoundException;
import com.newproject.repository.UsersRepo;

@Service
public class UsersService {

	@Autowired
	public UsersRepo usersRepo;
	
	@Autowired
	public Users users;
	
	public Users addUserData(Users u){
		String userName = usersRepo.findByUserName(u.username);
		if(userName==null || !userName.equals(u.username)) {
		users.setUserId(UUID.randomUUID().toString());
		users.setFirstName(u.firstName);
		users.setLastName(u.lastName);
		users.setEmail(u.email);
		users.setDate(u.date);
		users.setDateOfBirth(u.dateOfBirth);
		users.setPhoneNumber(u.phoneNumber);
		users.setUsername(u.username);
		users.setPassword(u.password);
		Users userdata = usersRepo.save(users);
		return userdata;
		}else {
			throw new AlreadyExistsException("userName already Exists");
		}
	}
	
	public Users fetchUserById(String userId) {
		Users userdata = usersRepo.findByUserId(userId);
		if(userdata!=null) {
		return userdata;
		}else {
			throw new ResourceNotFoundException("Id not found");
		}
	}
	
	public Users updateUser(Users users) {
		Users userdata = usersRepo.findByUserId(users.userId);
		if(userdata != null) {
			userdata.setUsername(users.username);
			userdata.setFirstName(users.firstName);
			userdata.setLastName(users.lastName);
			userdata.setPhoneNumber(users.phoneNumber);
			userdata.setPassword(users.password);
			userdata.setEmail(users.email);
			userdata.setDate(users.date);
			userdata.setDateOfBirth(users.dateOfBirth);
			userdata = 	usersRepo.save(userdata);
		}else {
			throw new ResourceNotFoundException("UserId does not exist");
		}
		return userdata;
	}
	
	public void deleteUser(String userId) {
		Users userdata = fetchUserById(userId);
		usersRepo.deleteById(userdata.userId);
	}
	
	public List<Users> findUsersBetweenRange(LocalDateTime startDate, LocalDateTime endDate){
		System.out.println("service class");
		System.out.println("usrslst "+usersRepo.findUsersBetweenRange(startDate, endDate));
		List<Users> userList = usersRepo.findUsersBetweenRange(startDate, endDate);
		System.out.println("userlist "+userList);
		return userList;
	}
}

