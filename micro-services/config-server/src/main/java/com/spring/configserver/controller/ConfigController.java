package com.spring.configserver.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ConfigController {


    @GetMapping("")
    public ResponseEntity<?> getConfig() {
        return ResponseEntity.ok("hello world");
    }


    @GetMapping("/config")
    public ResponseEntity<List<String>> listConfigs() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:config-repo/*");

        List<String> fileNames = Arrays.stream(resources)
                .map(Resource::getFilename)
                .collect(Collectors.toList());

        return ResponseEntity.ok(fileNames);

    }
}
