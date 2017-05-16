package com.epicsto.controllers;

import com.epicsto.entity.User;
import com.epicsto.json.GenericResponse;
import com.epicsto.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.IOException;

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

    private static ObjectMapper objectMapper ;


    @RequestMapping(method = RequestMethod.POST,value = "/register" ,headers = {"Accept=text/xml, application/json"})
    public @ResponseBody String registerUser(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @RequestBody String body){
        LOGGER.debug("Request /register(POST) body: {}",body);


        if(null == body){
            return gson.toJson(new GenericResponse("FAILURE",String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                    "Unauthorized Request"));
        }

        String userName = null ;
        String userPhone = null ;
        String userEmail = null ;
        try{
            JsonNode json = objectMapper.readTree(body);

            if(null != json.get("userName")){
                userName = json.get("userName").asText();
            }
            if(null != json.get("userEmail")){
                userEmail = json.get("userEmail").asText();
            }
            if(null != json.get("userPhone")){
                userPhone = json.get("userPhone").asText();
            }

        }catch(IOException e){
            LOGGER.error("error parsing the body of the request {} " , body );
            return gson.toJson(new GenericResponse("FAILURE",String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    "Bad Request"));
        }
        User user = new User();
        user.setUserEmail(userEmail);
        user.setUserName(userName);
        user.setUserPhone(userPhone);

        int userId = userService.createUser(user);

        if(userId>0){
            return gson.toJson(new GenericResponse("SUCCESS",String.valueOf(HttpStatus.OK.value()),
                    "User created with id : " + userId));
        }

        return  gson.toJson(new GenericResponse("SUCCESS",String.valueOf(HttpStatus.OK.value()),
                "User can not be created, try after some time"));
    }

}
