package com.tsi.project1.Language;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/languages")
public class LanguageController {

    @Autowired
    private LanguageRepository languageRepository;

    @GetMapping
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
}