package com.example.bucket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.comprehend.model.DetectKeyPhrasesResult;
import com.example.bucket.service.AmazonClient_Comprehend;

@RestController
@RequestMapping("/analyse/")
public class ComprehendController {

	@Autowired
	private AmazonClient_Comprehend amazonClientComprehend;;

	@GetMapping("/text")
	public DetectKeyPhrasesResult ListFile() {
		return this.amazonClientComprehend.analyse();
	}
}
