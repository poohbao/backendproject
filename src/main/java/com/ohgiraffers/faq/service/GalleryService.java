// src/main/java/com/ohgiraffers/faq/service/FaqService.java
package com.ohgiraffers.faq.service;

import com.ohgiraffers.faq.entity.GalleryEntity;
import com.ohgiraffers.faq.repository.GalleryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;

import java.util.List;
import java.util.Optional;
@Service
public class GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private GitHubService gitHubService;

    public GalleryEntity saveGallery( MultipartFile image, String title, String description ) throws Exception {
        String imageUrl = gitHubService.uploadImageToGitHub(image);
        GalleryEntity gallery = new GalleryEntity();
        gallery.setImageUrl(imageUrl);
        gallery.setTitle(title);
        gallery.setDescription(description);
        return galleryRepository.save(gallery);
    }

    public List<GalleryEntity> getAllGalleries() {
        return galleryRepository.findAll();
    }

    public void addGallery(GalleryEntity galleryEntity) {
        galleryRepository.save(galleryEntity);
    }
}
