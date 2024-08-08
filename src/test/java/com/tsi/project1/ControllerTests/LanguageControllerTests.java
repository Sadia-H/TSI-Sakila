package com.tsi.project1.ControllerTests;

import com.tsi.project1.language.Language;
import com.tsi.project1.language.LanguageController;
import com.tsi.project1.language.LanguageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LanguageControllerTests {

    private LanguageController languageController;
    private LanguageRepository mockRepository;

    @BeforeEach
    public void setup() {
        mockRepository = mock(LanguageRepository.class);
        languageController = new LanguageController(mockRepository);

        Language language1 = new Language((short)1, "English");
        Language language2 = new Language((short)2, "Spanish");

        List<Language> languages = Arrays.asList(language1, language2);
        when(mockRepository.findAll()).thenReturn(languages);

    }

    @Test
    public void languageControllerFindAllLanguages() {

        //call the languagecontroller method
        List<Language> result = languageController.getAllLanguages();

        //verify results
        assertNotNull(result, "The result should not be null.");
        assertEquals(2, result.size(), "The result should contain at least 2 languages.");

        assertEquals((short) 1, result.get(0).getLanguageId(), "The first language should be English.");
        assertEquals("English", result.get(0).getName(), "The first language should be English.");

        assertEquals((short) 2, result.get(1).getLanguageId(), "The second language should be Spanish.");
        assertEquals("Spanish", result.get(1).getName(), "The second language should be Spanish.");




    }


}
