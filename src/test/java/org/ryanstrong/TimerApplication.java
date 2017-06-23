package org.ryanstrong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootApplication
@RunWith(SpringRunner.class)
@SpringBootTest
public class TimerApplication {

	@Test
	public void contextLoads() {
	}

    public static void main(String [] args){
        SpringApplication.run(TimerApplication.class, args);
    }
}
