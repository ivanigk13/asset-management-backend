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

import com.lawencon.asset.dto.role.DeleteRoleDtoRes;
import com.lawencon.asset.dto.role.GetAllRoleDto;
import com.lawencon.asset.dto.role.GetByIdRoleDto;
import com.lawencon.asset.dto.role.InsertRoleDtoReq;
import com.lawencon.asset.dto.role.InsertRoleDtoRes;
import com.lawencon.asset.dto.role.UpdateRoleDtoReq;
import com.lawencon.asset.dto.role.UpdateRoleDtoRes;
import com.lawencon.asset.service.RolesService;

@RestController
@RequestMapping("roles")
public class RoleController {
	
	private RolesService roleService;
	
	@Autowired
	public void setRolesService(RolesService rolesService) {
		this.roleService = rolesService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllRoleDto> getAll() throws Exception{
		GetAllRoleDto result = roleService.getAll();
		return new ResponseEntity<GetAllRoleDto>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdRoleDto> getById(@PathVariable("id") Long id){
		GetByIdRoleDto result = roleService.getById(id);
		return new ResponseEntity<GetByIdRoleDto>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertRoleDtoRes> insert(@RequestBody @Valid InsertRoleDtoReq role){
		InsertRoleDtoRes result = roleService.insert(role);
		return new ResponseEntity<InsertRoleDtoRes>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateRoleDtoRes> update(@RequestBody @Valid UpdateRoleDtoReq role){
		UpdateRoleDtoRes result = roleService.update(role);
		return new ResponseEntity<UpdateRoleDtoRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRoleDtoRes> delete(@PathVariable("id") Long id) throws Exception{
		DeleteRoleDtoRes result = roleService.delete(id);
		return new ResponseEntity<DeleteRoleDtoRes>(result, HttpStatus.OK);
	}
	
}
