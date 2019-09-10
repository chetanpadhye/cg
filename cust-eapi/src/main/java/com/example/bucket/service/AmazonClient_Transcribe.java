package com.example.bucket.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.DetectDominantLanguageRequest;
import com.amazonaws.services.comprehend.model.DetectDominantLanguageResult;
import com.amazonaws.services.comprehend.model.DetectKeyPhrasesRequest;
import com.amazonaws.services.comprehend.model.DetectKeyPhrasesResult;
import com.amazonaws.services.comprehend.model.DominantLanguage;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;

@Service
public class AmazonClient_Transcribe {
	AmazonComprehend comprehendClient;

	@Value("${amazonProperties.endpointUrl}")
	private String endpointUrl;
	@Value("${amazonProperties.bucketName}")
	private String bucketName;
	@Value("${amazonProperties.accessKey}")
	private String accessKey;
	@Value("${amazonProperties.secretKey}")
	private String secretKey;

	private String text = "The following examples demonstrate how to use Amazon Comprehend Medical operations using the AWS CLI, Java, and Python. Use them to learn about Amazon Comprehend Medical operations and as building blocks for your own applications.";

	@PostConstruct
	private void initializeAmazon() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

		AWSCredentialsProvider aWSCredentialsProvider = new AWSStaticCredentialsProvider(credentials);

		this.comprehendClient = AmazonComprehendClientBuilder.standard().withCredentials(aWSCredentialsProvider)
				.withRegion("us-east-2").build();
	}

	public DetectKeyPhrasesResult analyse() {

		System.out.println("Calling DetectDominantLanguage");
		DetectDominantLanguageRequest detectDominantLanguageRequest = new DetectDominantLanguageRequest()
				.withText(this.text);
		DetectDominantLanguageResult detectDominantLanguageResult = comprehendClient
				.detectDominantLanguage(detectDominantLanguageRequest);

		DetectKeyPhrasesResult keyPhrases = comprehendClient
				.detectKeyPhrases(new DetectKeyPhrasesRequest().withText(this.text).withLanguageCode("en"));

		detectDominantLanguageResult.getLanguages().forEach(System.out::println);

		List<DominantLanguage> lang = detectDominantLanguageResult.getLanguages();
		System.out.println("Calling DetectDominantLanguage\n");
		System.out.println("Done");

		return keyPhrases;
	}

}
