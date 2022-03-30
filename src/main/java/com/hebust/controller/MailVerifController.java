package com.hebust.controller;

import com.hebust.service.MailVerifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailVerifController {

    @Autowired
    private MailVerifService service;


}
