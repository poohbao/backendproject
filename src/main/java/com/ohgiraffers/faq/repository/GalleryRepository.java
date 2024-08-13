// src/main/java/com/ohgiraffers/faq/repository/FaqRepository.java
package com.ohgiraffers.faq.repository;

import com.ohgiraffers.faq.entity.GalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GalleryRepository extends JpaRepository<GalleryEntity, Long> {
    @Query("SELECT G FROM GalleryEntity G ORDER BY G.displayOrder")
    List<GalleryEntity> findAllOrderedByDisplayOrder();
}
