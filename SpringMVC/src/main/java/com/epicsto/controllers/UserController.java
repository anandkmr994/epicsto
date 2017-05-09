package com.epicsto.controllers;

import com.epicsto.json.GenericResponse;
import com.epicsto.service.UserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 9/5/17.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService ;

    private static Gson gson = new Gson();

    private static ObjectMapper


    @RequestMapping(method = RequestMethod.POST,value = "/register" ,headers = {"Accept=text/xml, application/json"})
    public @ResponseBody ResponseEntity registerUser(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @RequestBody String body){
        LOGGER.debug("Request /register(POST) body: {}",body);


        if(null == body){
            return new ResponseEntity<String>(gson.toJson(new GenericResponse("FAILURE",String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                    "Unauthorized Request")),HttpStatus.UNAUTHORIZED);
        }

    }

}
