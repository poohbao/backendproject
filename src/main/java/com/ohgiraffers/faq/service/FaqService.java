// src/main/java/com/ohgiraffers/faq/service/FaqService.java
package com.ohgiraffers.faq.service;

import com.ohgiraffers.faq.entity.Faq;
import com.ohgiraffers.faq.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FaqService {
    @Autowired
    private FaqRepository faqRepository;

    public List<Faq> findAll() {
        return faqRepository.findAllOrderedByDisplayOrder();
    }

    public Optional<Faq> findById(Long id) {
        return faqRepository.findById(id);
    }

    public Faq save(Faq faq) {
        return faqRepository.save(faq);
    }

    public void deleteById(Long id) {
        faqRepository.deleteById(id);
        reorderDisplayOrder();
    }
    @Transactional
    public Faq updatePartial(Long id, Faq partialFaq) {
        Faq existingFaq = faqRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FAQ not found"));

        if (partialFaq.getQuestion() != null) {
            existingFaq.setQuestion(partialFaq.getQuestion());
        }
        if (partialFaq.getAnswer() != null) {
            existingFaq.setAnswer(partialFaq.getAnswer());
        }
        if (partialFaq.getDisplayOrder() != null) {
            existingFaq.setDisplayOrder(partialFaq.getDisplayOrder());
        }

        return faqRepository.save(existingFaq);
    }
    private void reorderDisplayOrder() {
        List<Faq> faqs = faqRepository.findAllOrderedByDisplayOrder();
        for (int i = 0; i < faqs.size(); i++) {
            Faq faq = faqs.get(i);
            faq.setDisplayOrder((long) (i + 1)); // 재정렬할 새 번호 설정
            faqRepository.save(faq);
        }
    }
}
