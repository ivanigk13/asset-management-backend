package com.lawencon.asset.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.asset.dao.RolesDao;
import com.lawencon.asset.dto.role.DeleteRoleDtoRes;
import com.lawencon.asset.dto.role.GetAllRoleDto;
import com.lawencon.asset.dto.role.GetByIdRoleDto;
import com.lawencon.asset.dto.role.InsertRoleDtoData;
import com.lawencon.asset.dto.role.InsertRoleDtoReq;
import com.lawencon.asset.dto.role.InsertRoleDtoRes;
import com.lawencon.asset.dto.role.RoleModelDto;
import com.lawencon.asset.dto.role.UpdateRoleDtoData;
import com.lawencon.asset.dto.role.UpdateRoleDtoReq;
import com.lawencon.asset.dto.role.UpdateRoleDtoRes;
import com.lawencon.asset.model.Roles;
import com.lawencon.asset.service.RolesService;

@Service
public class RolesServiceImpl extends BaseServiceImpl implements RolesService{

	private RolesDao rolesDao;
	
	@Autowired
	public RolesServiceImpl(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}

	@Override
	public GetAllRoleDto getAll() throws Exception {		
		GetAllRoleDto allRoleDto = new GetAllRoleDto();
		List<Roles> roles = rolesDao.getAll();
		List<RoleModelDto> datas = new ArrayList<RoleModelDto>();		
		for (Roles role : roles) {
			RoleModelDto data = new RoleModelDto();
			data.setId(role.getId());
			data.setRoleCode(role.getRoleCode());
			data.setRoleName(role.getRoleName());
			data.setVersion(role.getVersion());
			data.setIsActive(role.getIsActive());
			datas.add(data);
		}
		allRoleDto.setData(datas);
		return allRoleDto;
	}

	@Override
	public GetByIdRoleDto getById(Long id) {
		GetByIdRoleDto getByIdRoleDto = new GetByIdRoleDto();
		RoleModelDto data = new RoleModelDto();
		Roles role = rolesDao.getById(id);
		data.setId(role.getId());
		data.setRoleCode(role.getRoleCode());
		data.setRoleName(role.getRoleName());
		data.setVersion(role.getVersion());
		data.setIsActive(role.getIsActive());
		getByIdRoleDto.setData(data);
		return getByIdRoleDto;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertRoleDtoRes insert(InsertRoleDtoReq role) {
		InsertRoleDtoRes insertRoleDtoRes = new InsertRoleDtoRes();
		InsertRoleDtoData data = new InsertRoleDtoData();
		
		Roles roleInsert = new Roles();
		roleInsert.setRoleCode(role.getRoleCode());
		roleInsert.setRoleName(role.getRoleName());	
		roleInsert.setCreatedBy(getUserId());
		
		try {
			Roles newRole = rolesDao.save(roleInsert);
			if(newRole!=null) {
				data.setId(newRole.getId());
				insertRoleDtoRes.setData(data);
				insertRoleDtoRes.setMsg("berhasil insert role");
			}
			return insertRoleDtoRes;
		} catch (Exception e) {
			throw new RuntimeException("code sudah exist");
		}
		
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateRoleDtoRes update(UpdateRoleDtoReq role) {
		UpdateRoleDtoRes updateRoleDtoRes = new UpdateRoleDtoRes();
		UpdateRoleDtoData data = new UpdateRoleDtoData();
		
		Roles roleUpdate = rolesDao.getById(role.getId());
		roleUpdate.setRoleName(role.getRoleName());
		roleUpdate.setVersion(role.getVersion());
		roleUpdate.setUpdatedBy(getUserId());
		
		Roles newRole = rolesDao.save(roleUpdate);
		if(newRole!=null) {
			data.setVersion(newRole.getVersion());
			updateRoleDtoRes.setData(data);
			updateRoleDtoRes.setMsg("berhasil update role");
		}
		return updateRoleDtoRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteRoleDtoRes delete(Long id){
		DeleteRoleDtoRes deleteRoleDtoRes = new DeleteRoleDtoRes();
		try {
			Boolean result = rolesDao.delete(id);
			if(result) {
				deleteRoleDtoRes.setMsg("Berhasil delete");
			}
			return deleteRoleDtoRes;
		} catch (Exception e) {
			throw new RuntimeException("id sedang digunakan di entitas lain");
		}
	}
}
