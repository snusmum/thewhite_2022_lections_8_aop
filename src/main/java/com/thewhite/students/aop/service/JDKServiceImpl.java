package com.thewhite.students.aop.service;

import com.thewhite.students.aop.annotation.ValidateExecutionTime;
import com.thewhite.students.aop.annotation.LogExecutionTime;
import org.springframework.stereotype.Service;

@Service
public class JDKServiceImpl implements JDKService {

    @Override
    public String getInfo() {
        return "Hello from JDK";
    }

    @Override
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
