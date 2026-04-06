package com.newproject.controller;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newproject.entity.Users;
import com.newproject.repository.UsersRepo;
import com.newproject.service.UsersService;

@RestController
@RequestMapping("/User")
public class UsersController {

    private final UsersRepo usersRepo;

	@Autowired
	public UsersService usersService;

    UsersController(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }
	
	@PostMapping("/saveUser")
	public ResponseEntity<Users> saveUser(@RequestBody Users users) {
		Users userdata = usersService.addUserData(users);
		return ResponseEntity.status(HttpStatus.CREATED).body(userdata);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Users> getUserById(@PathVariable String userId) {
		Users userdata = usersService.fetchUserById(userId);
		return ResponseEntity.ok(userdata);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<Users> updateUserData(@RequestBody Users users){
		Users userdata = usersService.updateUser(users);
		return ResponseEntity.ok(userdata);
	}
	
	@DeleteMapping("/{userId}")
	public String deleteUserById(@PathVariable String userId){
		usersService.deleteUser(userId);
		return "User with "+userId+ " was deleted successfully";
	}
	
	@GetMapping("/listOfUsers")
	public ResponseEntity<List<Users>> findUsersInCertainRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate){
        System.out.println("controller class");
		return ResponseEntity.ok(usersService.findUsersBetweenRange(startDate, endDate));
	}
	
	@GetMapping("/listOfUsersUsingPagination")
	public Page<Users> UserDataByPagination(@RequestParam(defaultValue = "0")int pageNumber, @RequestParam(defaultValue = "5")int size){
		PageRequest pageable = PageRequest.of(pageNumber, size);
		 return usersRepo.findAll(pageable);
	}
}
