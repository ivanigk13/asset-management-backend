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

import com.lawencon.asset.dto.brand.DeleteBrandDtoRes;
import com.lawencon.asset.dto.brand.GetAllBrandDto;
import com.lawencon.asset.dto.brand.GetByIdBrandDto;
import com.lawencon.asset.dto.brand.InsertBrandDtoReq;
import com.lawencon.asset.dto.brand.InsertBrandDtoRes;
import com.lawencon.asset.dto.brand.UpdateBrandDtoReq;
import com.lawencon.asset.dto.brand.UpdateBrandDtoRes;
import com.lawencon.asset.service.BrandService;

@RestController
@RequestMapping("brands")
public class BrandController {
	
	private BrandService brandService;

	@Autowired
	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllBrandDto> getAll(){
		GetAllBrandDto result = brandService.getAll();
		return new ResponseEntity<GetAllBrandDto>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdBrandDto> getById(@PathVariable("id") Long id){
		GetByIdBrandDto result = brandService.getById(id);
		return new ResponseEntity<GetByIdBrandDto>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertBrandDtoRes> insert(@RequestBody @Valid InsertBrandDtoReq brand){
		InsertBrandDtoRes result = brandService.insert(brand);
		return new ResponseEntity<InsertBrandDtoRes>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateBrandDtoRes> update(@RequestBody @Valid UpdateBrandDtoReq brand){
		UpdateBrandDtoRes result = brandService.update(brand);
		return new ResponseEntity<UpdateBrandDtoRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteBrandDtoRes> delete(@PathVariable("id") Long id){
		DeleteBrandDtoRes result = brandService.delete(id);
		return new ResponseEntity<DeleteBrandDtoRes>(result, HttpStatus.OK);
	}

}
