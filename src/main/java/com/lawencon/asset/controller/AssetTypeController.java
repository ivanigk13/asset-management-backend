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

import com.lawencon.asset.dto.assettype.DeleteAssetTypeDtoRes;
import com.lawencon.asset.dto.assettype.GetAllAssetTypeDto;
import com.lawencon.asset.dto.assettype.GetByIdAssetTypeDto;
import com.lawencon.asset.dto.assettype.InsertAssetTypeDtoReq;
import com.lawencon.asset.dto.assettype.InsertAssetTypeDtoRes;
import com.lawencon.asset.dto.assettype.UpdateAssetTypeDtoReq;
import com.lawencon.asset.dto.assettype.UpdateAssetTypeDtoRes;
import com.lawencon.asset.service.AssetTypeService;

@RestController
@RequestMapping("asset-types")
public class AssetTypeController {

	private AssetTypeService assetTypeService;

	@Autowired
	public void setAssetTypeService(AssetTypeService assetTypeService) {
		this.assetTypeService = assetTypeService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllAssetTypeDto> getAll(){
		GetAllAssetTypeDto result = assetTypeService.getAll();
		return new ResponseEntity<GetAllAssetTypeDto>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdAssetTypeDto> getById(@PathVariable("id") Long id){
		GetByIdAssetTypeDto result = assetTypeService.getById(id);
		return new ResponseEntity<GetByIdAssetTypeDto>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertAssetTypeDtoRes> insert(@RequestBody @Valid InsertAssetTypeDtoReq assetType){
		InsertAssetTypeDtoRes result = assetTypeService.insert(assetType);
		return new ResponseEntity<InsertAssetTypeDtoRes>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateAssetTypeDtoRes> update(@RequestBody @Valid UpdateAssetTypeDtoReq assetType){
		UpdateAssetTypeDtoRes result = assetTypeService.update(assetType);
		return new ResponseEntity<UpdateAssetTypeDtoRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteAssetTypeDtoRes> delete(@PathVariable("id") Long id){
		DeleteAssetTypeDtoRes result = assetTypeService.delete(id);
		return new ResponseEntity<DeleteAssetTypeDtoRes>(result, HttpStatus.OK);
	}
}
