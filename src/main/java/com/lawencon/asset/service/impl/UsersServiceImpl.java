package com.lawencon.asset.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.asset.constant.RoleList;
import com.lawencon.asset.dao.UsersDao;
import com.lawencon.asset.dto.user.DeleteUserDtoRes;
import com.lawencon.asset.dto.user.GetAllUserDto;
import com.lawencon.asset.dto.user.GetByIdUserDto;
import com.lawencon.asset.dto.user.GetUnemployeeDto;
import com.lawencon.asset.dto.user.InsertUserDtoData;
import com.lawencon.asset.dto.user.InsertUserDtoReq;
import com.lawencon.asset.dto.user.InsertUserDtoRes;
import com.lawencon.asset.dto.user.LoginDtoRes;
import com.lawencon.asset.dto.user.UpdateUserDtoData;
import com.lawencon.asset.dto.user.UpdateUserDtoReq;
import com.lawencon.asset.dto.user.UpdateUserDtoRes;
import com.lawencon.asset.dto.user.UserModelDto;
import com.lawencon.asset.email.EmailService;
import com.lawencon.asset.generatepassword.GeneratorPassword;
import com.lawencon.asset.model.Roles;
import com.lawencon.asset.model.Users;
import com.lawencon.asset.model.pojo.EmailPojo;
import com.lawencon.asset.service.UsersService;

@Service
public class UsersServiceImpl extends BaseServiceImpl implements UsersService{

	private UsersDao userDao;
	private PasswordEncoder passwordEncoder;
	private EmailService emailService;

	@Autowired
	public UsersServiceImpl(UsersDao userDao, EmailService emailService) {
		this.userDao = userDao;
		this.emailService = emailService;
	}
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}	

	@Override
	public GetAllUserDto getAll() {
		GetAllUserDto allUserDto = new GetAllUserDto();
		List<UserModelDto> datas = new ArrayList<>();
		List<Users> users = userDao.getAll();
		for (Users user : users) {
			UserModelDto data = new UserModelDto();
			data.setId(user.getId());
			data.setVersion(user.getVersion());
			data.setRoleName(user.getRole().getRoleName());
			data.setUsername(user.getUsername());
			data.setPassword(user.getPassword());
			data.setEmail(user.getEmail());
			data.setIsActive(user.getIsActive());
			datas.add(data);
		}
		allUserDto.setData(datas);
		return allUserDto;
	}

	@Override
	public GetByIdUserDto getById(Long id) {
		GetByIdUserDto getByIdUserDto = new GetByIdUserDto();
		UserModelDto data = new UserModelDto();
		Users user = userDao.getById(id);
		data.setId(user.getId());
		data.setRoleId(user.getRole().getId());
		data.setRoleName(user.getRole().getRoleName());
		data.setUsername(user.getUsername());
		data.setPassword(user.getPassword());
		data.setEmail(user.getEmail());
		data.setVersion(user.getVersion());
		data.setIsActive(user.getIsActive());
		getByIdUserDto.setData(data);
		return getByIdUserDto;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertUserDtoRes insert(InsertUserDtoReq user) {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		
		InsertUserDtoRes insertUserDtoRes = new InsertUserDtoRes();
		InsertUserDtoData data = new InsertUserDtoData();
		GeneratorPassword generatorPassword = new GeneratorPassword();
		String password = generatorPassword.generatePassword();
		Users userInsert = new Users();
		Roles role = new Roles();
		role.setId(user.getRoleId());
		role.setVersion(0);
		userInsert.setRole(role);
		userInsert.setUsername(user.getUsername());
		userInsert.setPassword(passwordEncoder.encode(password));
		userInsert.setEmail(user.getEmail());
		userInsert.setCreatedBy(RoleList.SUPER_ADMIN.id);
		
		EmailPojo emailPojo = new EmailPojo();
		emailPojo.setEmail(user.getEmail());
		emailPojo.setSubject("this is subject for generate password");
		emailPojo.setBody("Hi, this is your generated password: "+password);
		try {
			emailService.sendEmail(emailPojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		executorService.shutdown();
		
		Users newUser = userDao.save(userInsert);
		if(newUser!=null) {
			data.setId(newUser.getId());
			insertUserDtoRes.setData(data);
			insertUserDtoRes.setMsg("berhasil insert user");
		}
		return insertUserDtoRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateUserDtoRes update(UpdateUserDtoReq user) {
		UpdateUserDtoRes updateUserDtoRes = new UpdateUserDtoRes();
		UpdateUserDtoData data = new UpdateUserDtoData();
		Long id = user.getId();
		Long roleId = user.getRoleId();
		String username = user.getUsername();
		String password = user.getPassword();
		Integer version = user.getVersion();
		Boolean isActive = user.getIsActive();
		Long userUpdatedById = getUserId();
		
		Users userUpdate = userDao.getById(id);
		Roles role = new Roles();
		if(roleId!=null) {
			role.setId(roleId);
			role.setVersion(0);
		}else {
			role = null;
		}
		userUpdate.setRole(role);
		userUpdate.setUsername(username);
		userUpdate.setPassword(password);
		userUpdate.setVersion(version);
		userUpdate.setIsActive(isActive);
		userUpdate.setUpdatedBy(userUpdatedById);
		
		Users newUser = userDao.save(userUpdate);
		if(newUser!=null) {
			data.setVersion(newUser.getVersion());
			updateUserDtoRes.setData(data);
			updateUserDtoRes.setMsg("berhasil update user");
		}
		return updateUserDtoRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteUserDtoRes delete(Long id) {
		DeleteUserDtoRes deleteUserDtoRes = new DeleteUserDtoRes();
		Boolean result = userDao.delete(id);
		if(result) deleteUserDtoRes.setMsg("Berhasil delete");
		else deleteUserDtoRes.setMsg("Gagal delete");
		return deleteUserDtoRes;
	}

	@Override
	public GetUnemployeeDto getUnemployee() {
		GetUnemployeeDto getUnemployee = new GetUnemployeeDto();
		List<UserModelDto> datas = new ArrayList<UserModelDto>();
		List<Users> users = userDao.getUnemployee();
		for (Users user : users) {
			UserModelDto data = new UserModelDto();
			data.setId(user.getId());
			data.setVersion(user.getVersion());
			data.setRoleName(user.getRole().getRoleName());
			data.setUsername(user.getUsername());
			data.setPassword(user.getPassword());
			data.setEmail(user.getEmail());
			data.setIsActive(user.getIsActive());
			datas.add(data);
		}
		getUnemployee.setData(datas);
		return getUnemployee;
	}

	@Override
	public LoginDtoRes login(String email) {
		LoginDtoRes loginDtoRes = new LoginDtoRes();
		Users user = userDao.getByEmail(email);
		loginDtoRes.setId(user.getId());
		loginDtoRes.setRoleCode(user.getRole().getRoleCode());
		loginDtoRes.setUsername(user.getUsername());
		return loginDtoRes;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username==null) {
			throw new UsernameNotFoundException("invalid email and password");
		}
		Users user = userDao.getByEmail(username);
		return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}

}
