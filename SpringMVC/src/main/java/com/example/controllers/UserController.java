package com.example.controllers;

import com.example.entity.User;
import com.example.json.GenericResponse;
import com.example.service.UserService;
import com.example.utils.GsonSerializer;
import com.example.utils.ObjectMapperUtility;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 9/5/17.
 */
@RestController
//@Api(value = "user api")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService ;


    @RequestMapping(method = RequestMethod.POST,value = "/user/register" ,headers = {"Accept=text/xml, application/json"})
    /*@ApiOperation(value = "/user/register api",
            notes = "used to register user in example",
            httpMethod = "POST",
            produces = "a json with response code and message",
            consumes = "a json with user name, user email, user phone")
            */
    public @ResponseBody String registerUser(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @RequestBody String body){
        LOGGER.debug("Request /register(POST) body: {}",body);


        if(null == body){
            return GsonSerializer.getInstance().toJson(new GenericResponse("FAILURE",String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                    "Unauthorized Request"));
        }

        String userName = null ;
        String userPhone = null ;
        String userEmail = null ;
        try{
            JsonNode json = ObjectMapperUtility.getInstance().readTree(body);

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
            return GsonSerializer.getInstance().toJson(new GenericResponse("FAILURE",String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    "Bad Request"));
        }

        if(null == userEmail || null == userName || null == userPhone){
            LOGGER.error("mandatory fields are missing in the body {} " , body );
            return GsonSerializer.getInstance().toJson(new GenericResponse("FAILURE",String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    "Bad Request"));
        }
        User user = new User();
        user.setUserEmail(userEmail);
        user.setUserName(userName);
        user.setUserPhone(userPhone);

        int userId = userService.createUser(user);

        if(userId>0){
            return GsonSerializer.getInstance().toJson(new GenericResponse("SUCCESS",String.valueOf(HttpStatus.OK.value()),
                    "User created with id : " + userId));
        }

        return  GsonSerializer.getInstance().toJson(new GenericResponse("SUCCESS",String.valueOf(HttpStatus.OK.value()),
                "User can not be created, try after some time"));
    }

}
