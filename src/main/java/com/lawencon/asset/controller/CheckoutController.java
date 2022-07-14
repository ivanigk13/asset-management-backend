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

import com.lawencon.asset.dto.checkout.GetAllCheckoutDto;
import com.lawencon.asset.dto.checkout.GetByIdCheckoutDto;
import com.lawencon.asset.dto.checkout.GetCheckoutExpiredDto;
import com.lawencon.asset.dto.checkout.GetUncheckinDto;
import com.lawencon.asset.dto.checkout.InsertCheckoutDtoReq;
import com.lawencon.asset.dto.checkout.InsertCheckoutDtoRes;
import com.lawencon.asset.service.CheckoutService;

@RestController
@RequestMapping("checkouts")
public class CheckoutController {

	private CheckoutService checkoutService;

	@Autowired
	public void setCheckoutService(CheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllCheckoutDto> getAll(){
		GetAllCheckoutDto result = checkoutService.getAll();
		return new ResponseEntity<GetAllCheckoutDto>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdCheckoutDto> getById(@PathVariable("id") Long id){
		GetByIdCheckoutDto result = checkoutService.getById(id);
		return new ResponseEntity<GetByIdCheckoutDto>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertCheckoutDtoRes> insert(@RequestBody @Valid InsertCheckoutDtoReq checkout){
		InsertCheckoutDtoRes result = checkoutService.insert(checkout);
		return new ResponseEntity<InsertCheckoutDtoRes>(result, HttpStatus.CREATED);
	}
	
	@GetMapping("uncheckin")
	public ResponseEntity<GetUncheckinDto> getUncheckin(){
		GetUncheckinDto result = checkoutService.getUncheckin();
		return new ResponseEntity<GetUncheckinDto>(result, HttpStatus.OK);
	}
	
	@GetMapping("expired")
	public ResponseEntity<GetCheckoutExpiredDto> getCheckoutExpired(){
		GetCheckoutExpiredDto result = checkoutService.getCheckoutExpired();
		return new ResponseEntity<GetCheckoutExpiredDto>(result, HttpStatus.OK);
	}
	
}
