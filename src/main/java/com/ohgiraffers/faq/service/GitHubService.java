package com.ohgiraffers.faq.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubService {

    @Value("${GITHUB_API_URL}")
    private static String GITHUB_API_URL;
    @Value("${GITHUB_TOKEN}")
    private static String GITHUB_TOKEN;

    public String uploadImageToGitHub(MultipartFile image) throws Exception {
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        String url = GITHUB_API_URL + fileName;

        byte[] imageBytes = IOUtils.toByteArray(image.getInputStream());
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("message", "Upload image via API");
        requestBody.put("content", base64Image);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + GITHUB_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseBody = response.getBody();
            return (String) ((Map) responseBody.get("content")).get("download_url");
        } else {
            throw new RuntimeException("Failed to upload image to GitHub");
        }
    }
}
