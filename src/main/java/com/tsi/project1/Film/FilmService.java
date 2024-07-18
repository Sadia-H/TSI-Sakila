package com.tsi.project1.Film;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@Transactional
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;




}
