package com.example.domaindriverdesign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;


@SpringBootApplication(scanBasePackages={"com.example.domaindriverdesign.shared.event"})
public class DomaindriverdesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomaindriverdesignApplication.class, args);

    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
    }

}
