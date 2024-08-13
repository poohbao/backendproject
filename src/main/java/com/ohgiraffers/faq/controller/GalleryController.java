package com.ohgiraffers.faq.controller;

import com.ohgiraffers.faq.dto.GalleryDTO;
import com.ohgiraffers.faq.entity.GalleryEntity;
import com.ohgiraffers.faq.service.GalleryService;
import com.ohgiraffers.faq.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gallery")
@CrossOrigin(origins = "http://localhost:3000")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;
    @Autowired
    private GitHubService gitHubService;

    @GetMapping
    public List<GalleryDTO> getAllGalleries() {
        return galleryService.getAllGalleries().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @PostMapping("/api/gallery")
    public ResponseEntity<?> addImage(
            @RequestParam("image_url") String image_url,
            @RequestParam("title") String title,
            @RequestParam("description") String description)
             {
                 try {
                     // 이미지 업로드 로직
                     return ResponseEntity.ok().body(Collections.singletonMap("message", "Image uploaded successfully"));
                 } catch (Exception e) {
                     // 오류 발생 시 JSON 형태로 응답
                     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(Collections.singletonMap("message", "Image upload failed"));
                 }
    }

    private GalleryDTO convertToDTO(GalleryEntity entity) {
        GalleryDTO dto = new GalleryDTO();
        dto.setId(entity.getId());
        dto.setImageUrl(entity.getImageUrl());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        System.out.println("dto: " + dto);

        return dto;
    }

    private GalleryEntity convertToEntity(GalleryDTO dto) {
        GalleryEntity entity = new GalleryEntity();
        entity.setId(dto.getId());
        entity.setImageUrl(dto.getImageUrl());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
