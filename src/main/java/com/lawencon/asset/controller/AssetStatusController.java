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

import com.lawencon.asset.dto.assetstatus.DeleteAssetStatusDtoRes;
import com.lawencon.asset.dto.assetstatus.GetAllAssetStatusDto;
import com.lawencon.asset.dto.assetstatus.GetByIdAssetStatusDto;
import com.lawencon.asset.dto.assetstatus.InsertAssetStatusDtoReq;
import com.lawencon.asset.dto.assetstatus.InsertAssetStatusDtoRes;
import com.lawencon.asset.dto.assetstatus.UpdateAssetStatusDtoReq;
import com.lawencon.asset.dto.assetstatus.UpdateAssetStatusDtoRes;
import com.lawencon.asset.service.AssetStatusService;

@RestController
@RequestMapping("asset-statuses")
public class AssetStatusController {

	private AssetStatusService assetStatusService;

	@Autowired
	public void setAssetStatusService(AssetStatusService assetStatusService) {
		this.assetStatusService = assetStatusService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllAssetStatusDto> getAll(){
		GetAllAssetStatusDto result = assetStatusService.getAll();
		return new ResponseEntity<GetAllAssetStatusDto>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdAssetStatusDto> getById(@PathVariable("id") Long id){
		GetByIdAssetStatusDto result = assetStatusService.getById(id);
		return new ResponseEntity<GetByIdAssetStatusDto>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertAssetStatusDtoRes> insert(@RequestBody @Valid InsertAssetStatusDtoReq assetStatus){
		InsertAssetStatusDtoRes result = assetStatusService.insert(assetStatus);
		return new ResponseEntity<InsertAssetStatusDtoRes>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateAssetStatusDtoRes> update(@RequestBody @Valid UpdateAssetStatusDtoReq assetStatus){
		UpdateAssetStatusDtoRes result = assetStatusService.update(assetStatus);
		return new ResponseEntity<UpdateAssetStatusDtoRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteAssetStatusDtoRes> delete(@PathVariable("id") Long id){
		DeleteAssetStatusDtoRes result = assetStatusService.delete(id);
		return new ResponseEntity<DeleteAssetStatusDtoRes>(result, HttpStatus.OK);
	}
}
