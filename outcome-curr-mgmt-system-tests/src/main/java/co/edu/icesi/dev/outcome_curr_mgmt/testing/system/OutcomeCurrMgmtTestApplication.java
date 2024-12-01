package co.edu.icesi.dev.outcome_curr_mgmt.testing.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OutcomeCurrMgmtTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutcomeCurrMgmtTestApplication.class, args);
    }
}