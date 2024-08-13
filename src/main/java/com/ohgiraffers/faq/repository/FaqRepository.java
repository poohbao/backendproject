// src/main/java/com/ohgiraffers/faq/repository/FaqRepository.java
package com.ohgiraffers.faq.repository;

import com.ohgiraffers.faq.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {

    @Query("SELECT f FROM Faq f ORDER BY f.displayOrder")
    List<Faq> findAllOrderedByDisplayOrder();
}
