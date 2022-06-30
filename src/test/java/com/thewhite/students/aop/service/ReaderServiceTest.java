package com.thewhite.students.aop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReaderServiceTest {

    @Autowired
    private ReaderService readerService;

    @Test
    void testSave() {
        readerService.saveReader("test");
    }

}