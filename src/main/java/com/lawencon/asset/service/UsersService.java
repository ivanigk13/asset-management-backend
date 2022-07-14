package com.lawencon.asset.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lawencon.asset.dto.user.DeleteUserDtoRes;
import com.lawencon.asset.dto.user.GetAllUserDto;
import com.lawencon.asset.dto.user.GetByIdUserDto;
import com.lawencon.asset.dto.user.GetUnemployeeDto;
import com.lawencon.asset.dto.user.InsertUserDtoReq;
import com.lawencon.asset.dto.user.InsertUserDtoRes;
import com.lawencon.asset.dto.user.LoginDtoRes;
import com.lawencon.asset.dto.user.UpdateUserDtoReq;
import com.lawencon.asset.dto.user.UpdateUserDtoRes;

public interface UsersService extends UserDetailsService{
	GetAllUserDto getAll();
	GetByIdUserDto getById(Long id);
	InsertUserDtoRes insert(InsertUserDtoReq user) throws Exception;
	UpdateUserDtoRes update(UpdateUserDtoReq user);
	DeleteUserDtoRes delete(Long id);
	LoginDtoRes login(String email);
	GetUnemployeeDto getUnemployee();
}
