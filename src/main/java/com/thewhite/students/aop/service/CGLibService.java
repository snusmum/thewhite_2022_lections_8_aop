package com.thewhite.students.aop.service;

import com.thewhite.students.aop.annotation.ValidateExecutionTime;
import com.thewhite.students.aop.annotation.LogExecutionTime;
import org.springframework.stereotype.Service;

@Service
public class CGLibService {

    public String getInfo() {
        return "Hello from CGLib";
    }

    @ValidateExecutionTime
    @LogExecutionTime
    public void waitForMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
