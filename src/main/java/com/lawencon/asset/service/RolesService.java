package com.lawencon.asset.service;

import com.lawencon.asset.dto.role.DeleteRoleDtoRes;
import com.lawencon.asset.dto.role.GetAllRoleDto;
import com.lawencon.asset.dto.role.GetByIdRoleDto;
import com.lawencon.asset.dto.role.InsertRoleDtoReq;
import com.lawencon.asset.dto.role.InsertRoleDtoRes;
import com.lawencon.asset.dto.role.UpdateRoleDtoReq;
import com.lawencon.asset.dto.role.UpdateRoleDtoRes;

public interface RolesService {
	GetAllRoleDto getAll() throws Exception;
	InsertRoleDtoRes insert(InsertRoleDtoReq role);
	UpdateRoleDtoRes update(UpdateRoleDtoReq role);
	GetByIdRoleDto getById(Long id);
	DeleteRoleDtoRes delete(Long id) throws Exception;
}
