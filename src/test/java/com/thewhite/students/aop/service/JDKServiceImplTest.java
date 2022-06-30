package com.thewhite.students.aop.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JDKServiceImplTest {

    @Autowired
    private JDKService service;

    @Test
    void testGetInfo() {
        // Arrange
        String dateString = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        // Act
        String result = service.getInfo();

        // Assert
        assertEquals(dateString + ": Hello from JDK", result);
    }

    @Test
    void testDuration() {
        // Arrange
        PrintStream console = System.out;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteStream));

        // Act
        service.waitForMillis(1001);

        // Assert
        System.setOut(console);
        String result = byteStream.toString();
        System.out.println(result);
        assertTrue(result.contains("call void com.thewhite.students.aop.service.JDKService.waitForMillis(long) with arguments [1001]"));
        assertTrue(result.contains("Time spend: "));
    }

    @Test
    void failOnNegativeDuration() {
        try {
            service.waitForMillis(-10);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Assertions.assertThatThrownBy(() -> service.waitForMillis(-10)).hasMessage("Negative duration (-10)");
    }
}