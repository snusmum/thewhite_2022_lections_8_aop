package com.thewhite.students.aop.service;

import com.thewhite.students.aop.annotation.LogExecutionTime;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ReaderService {

    Map<String, Object> readers = new HashMap<>();

//    @LogExecutionTime
    public void saveReader(String name) {
        saveReaderInternal(name, null, null);
    }

    public void saveReader(String name, String status) {
        saveReaderInternal(name, status, null);
    }

    public void saveReader(String name, String status, boolean male) {
        saveReaderInternal(name, status, male);
    }

    @LogExecutionTime
    private void saveReaderInternal(String name, String status, Boolean male) {
        readers.put(UUID.randomUUID().toString(), new Object[]{name, status, male});
    }
}
