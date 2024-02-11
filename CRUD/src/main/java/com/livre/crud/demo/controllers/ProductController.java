package com.livre.crud.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Products")
public class ProductController {
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllProducts () {
        return ResponseEntity.ok("deu certo");
    }
}
