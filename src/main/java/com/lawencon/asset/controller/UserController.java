package com.lawencon.asset.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.asset.dto.user.DeleteUserDtoRes;
import com.lawencon.asset.dto.user.GetAllUserDto;
import com.lawencon.asset.dto.user.GetByIdUserDto;
import com.lawencon.asset.dto.user.GetUnemployeeDto;
import com.lawencon.asset.dto.user.InsertUserDtoReq;
import com.lawencon.asset.dto.user.InsertUserDtoRes;
import com.lawencon.asset.dto.user.UpdateUserDtoReq;
import com.lawencon.asset.dto.user.UpdateUserDtoRes;
import com.lawencon.asset.service.UsersService;

@RestController
@RequestMapping("users")
public class UserController {

	private UsersService userService;

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.userService = usersService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllUserDto> getAll(){
		GetAllUserDto result = userService.getAll();
		return new ResponseEntity<GetAllUserDto>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdUserDto> getById(@PathVariable("id") Long id){
		GetByIdUserDto result = userService.getById(id);
		return new ResponseEntity<GetByIdUserDto>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertUserDtoRes> insert(@RequestBody @Valid InsertUserDtoReq users) throws Exception{
		InsertUserDtoRes result = userService.insert(users);
		return new ResponseEntity<InsertUserDtoRes>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateUserDtoRes> update(@RequestBody @Valid UpdateUserDtoReq users){
		UpdateUserDtoRes result = userService.update(users);
		return new ResponseEntity<UpdateUserDtoRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteUserDtoRes> delete(@PathVariable("id") Long id){
		DeleteUserDtoRes result = userService.delete(id);
		return new ResponseEntity<DeleteUserDtoRes>(result, HttpStatus.OK);
	}
	
	@GetMapping("unemployee")
	public ResponseEntity<GetUnemployeeDto> getUnemployee(){
		GetUnemployeeDto result = userService.getUnemployee();
		return new ResponseEntity<GetUnemployeeDto>(result, HttpStatus.OK);
	}
	
}
