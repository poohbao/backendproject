// src/main/java/com/example/faq/FaqController.java
package com.ohgiraffers.faq.controller;

import com.ohgiraffers.faq.entity.Faq;
import com.ohgiraffers.faq.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faq")
@CrossOrigin(origins = "http://localhost:3000")
public class FaqController {
    @Autowired
    private FaqService faqService;

    @GetMapping()
    public List<Faq> getAllFaqs() {
        return faqService.findAll();
    }

    @PostMapping()
    public Faq createFaq(@RequestBody Faq faq) {
        return faqService.save(faq);
    }
    @PatchMapping("/{id}")
    public Faq updateFaq(@PathVariable Long id, @RequestBody Faq partialFaq) {
        return faqService.updatePartial(id, partialFaq);
    }

    @DeleteMapping("/{id}")
    public void deleteFaq(@PathVariable Long id) {
        faqService.deleteById(id);
    }
}
