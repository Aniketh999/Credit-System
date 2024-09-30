package com.qp.quantum_share.helper;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.qp.quantum_share.configuration.ConfigurationClass;
import com.qp.quantum_share.exception.CommonException;

@Component
public class UploadProfileToServer {
	@Autowired
	ConfigurationClass configuration;

	private String BUCKET_NAME = "quantumshare-user-profile";
	private String AWS_REGION = "ap-south-1";

	public String uploadFile(MultipartFile file) {
		try {
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(AWS_REGION).build();
			String fileName = file.getOriginalFilename();
			String contentType = file.getContentType();
			String key = "public/" + fileName + "." + contentType;
			ObjectMetadata metadata = configuration.getMetaObject();
			metadata.setContentType(file.getContentType());
			PutObjectRequest request = new PutObjectRequest(BUCKET_NAME, key, file.getInputStream(), metadata);
			PutObjectResult result = s3Client.putObject(request);

			String publicUrl = s3Client.getUrl(BUCKET_NAME, key).toString();
			return publicUrl;
		} catch (IOException e) {
			throw new CommonException(e.getMessage());
		}
	}
}
