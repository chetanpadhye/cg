package com.example.bucket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.ObjectListing;
import com.example.bucket.service.AmazonClient_S3;

@RestController
@RequestMapping("/storage/")
public class BucketController {

	private AmazonClient_S3 amazonClient;

	@Autowired
	BucketController(AmazonClient_S3 amazonClient) {
		this.amazonClient = amazonClient;
	}

	@PostMapping("/uploadFile")
	public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
		return this.amazonClient.uploadFile(file);
	}

	@DeleteMapping("/deleteFile")
	public String deleteFile(@RequestPart(value = "url") String fileUrl) {
		return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
	}

	@GetMapping("/buckets")
	public ObjectListing ListFile() {
		return this.amazonClient.listS3Buckets();
	}
}
