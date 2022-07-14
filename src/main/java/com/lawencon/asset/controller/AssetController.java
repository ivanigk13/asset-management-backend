package com.lawencon.asset.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.asset.dto.asset.DeleteAssetDtoRes;
import com.lawencon.asset.dto.asset.GetAllAssetDto;
import com.lawencon.asset.dto.asset.GetByIdAssetDto;
import com.lawencon.asset.dto.asset.GetExpiredAssetDto;
import com.lawencon.asset.dto.asset.GetReadyToUseDto;
import com.lawencon.asset.dto.asset.InsertAssetDtoRes;
import com.lawencon.asset.dto.asset.UpdateAssetDtoRes;
import com.lawencon.asset.service.AssetService;

@RestController
@RequestMapping("assets")
public class AssetController {

	private AssetService assetService;

	@Autowired
	public void setAssetService(AssetService assetService) {
		this.assetService = assetService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllAssetDto> getAll() throws IOException{
		GetAllAssetDto result = assetService.getAll();
		return new ResponseEntity<GetAllAssetDto>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdAssetDto> getById(@PathVariable("id") Long id){
		GetByIdAssetDto result = assetService.getById(id);
		return new ResponseEntity<GetByIdAssetDto>(result, HttpStatus.OK);
	}

	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
							MediaType.APPLICATION_JSON_VALUE,
							MediaType.IMAGE_JPEG_VALUE,
							MediaType.IMAGE_PNG_VALUE})
	public ResponseEntity<InsertAssetDtoRes> insert(
						@RequestPart(name = "content") String asset, 
						@RequestPart(required = false) MultipartFile file) throws IOException{
		
		InsertAssetDtoRes result = assetService.insert(asset, file);
		return new ResponseEntity<InsertAssetDtoRes>(result, HttpStatus.CREATED);
	}
	
	@PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
							MediaType.APPLICATION_JSON_VALUE,
							MediaType.IMAGE_JPEG_VALUE,
							MediaType.IMAGE_PNG_VALUE})
	public ResponseEntity<UpdateAssetDtoRes> update(
			@RequestPart(name = "content") String asset, 
			@RequestPart(required = false) MultipartFile file) throws IOException{
		
		UpdateAssetDtoRes result = assetService.update(asset, file);
		return new ResponseEntity<UpdateAssetDtoRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteAssetDtoRes> delete(@PathVariable("id") Long id){
		DeleteAssetDtoRes result = assetService.delete(id);
		return new ResponseEntity<DeleteAssetDtoRes>(result, HttpStatus.OK);
	}
	
	@GetMapping("ready")
	public ResponseEntity<GetReadyToUseDto> getReadyToUse() throws IOException{
		GetReadyToUseDto result = assetService.getReadyToUse();
		return new ResponseEntity<GetReadyToUseDto>(result, HttpStatus.OK);
	}
	
	@GetMapping("expired")
	public ResponseEntity<GetExpiredAssetDto> getExpiredAsset() throws IOException{
		GetExpiredAssetDto result = assetService.getExpiredAsset();
		return new ResponseEntity<GetExpiredAssetDto>(result, HttpStatus.OK);
	}
	
}
