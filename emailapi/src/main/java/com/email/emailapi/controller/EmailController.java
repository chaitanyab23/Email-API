package com.email.emailapi.controller;

import com.email.emailapi.model.EmailRequest;
import com.email.emailapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/welcome")
    public String welcome(){
        return "Hello this is my email api";
    }
    //api to send emailsendemail
    @RequestMapping(value = "/sendemail", method = RequestMethod.POST)
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request){


        System.out.println(request);
        boolean result= this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
        if(result){
            return ResponseEntity.ok("Email is Sent Successfully");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent");
        }
    }
}
