package com.suyh611.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SuyhController implements InitializingBean {
    @Value("${com.suyh:UNKNOWN-VALUE}")
    private String suyhValue;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("suyhValue: {}", suyhValue);
    }
}
