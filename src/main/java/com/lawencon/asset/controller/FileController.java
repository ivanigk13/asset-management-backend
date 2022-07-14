package com.lawencon.asset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.asset.dto.file.GetByIdFileDto;
import com.lawencon.asset.service.FileService;

@RestController
@RequestMapping("files")
public class FileController {

	private FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable("id") Long id){
		GetByIdFileDto file = fileService.getById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=attachment." + file.getData().getExtensionName());

        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_OCTET_STREAM).body(file.getData().getContent());
	}
}
