package com.lawencon.asset.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.asset.dto.checkin.GetAllCheckinDto;
import com.lawencon.asset.dto.checkin.GetByIdCheckinDto;
import com.lawencon.asset.dto.checkin.InsertCheckinDtoReq;
import com.lawencon.asset.dto.checkin.InsertCheckinDtoRes;
import com.lawencon.asset.service.CheckinService;

@RestController
@RequestMapping("checkins")
public class CheckinController {

	private CheckinService checkinService;

	@Autowired
	public void setCheckinService(CheckinService checkinService) {
		this.checkinService = checkinService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllCheckinDto> getAll(){
		GetAllCheckinDto result = checkinService.getAll();
		return new ResponseEntity<GetAllCheckinDto>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdCheckinDto> getById(@PathVariable("id") Long id){
		GetByIdCheckinDto result = checkinService.getById(id);
		return new ResponseEntity<GetByIdCheckinDto>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertCheckinDtoRes> insert(@RequestBody @Valid InsertCheckinDtoReq checkin){
		InsertCheckinDtoRes result = checkinService.insert(checkin);
		return new ResponseEntity<InsertCheckinDtoRes>(result, HttpStatus.CREATED);
	}
	
}
