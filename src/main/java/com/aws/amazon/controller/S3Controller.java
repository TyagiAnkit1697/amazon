package com.aws.amazon.controller;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/aws/s3")
public class S3Controller {

    @Autowired
    private AmazonS3 s3;

    public void get(MultipartFile file){
       
    }
}
