package com.epicsto.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by anandkumar on 17/5/17.
 */
public class ObjectMapperUtility {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectMapperUtility.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final ObjectMapperUtility INSTANCE = new ObjectMapperUtility();

    public static ObjectMapperUtility getInstance(){
        return INSTANCE ;
    }

    public JsonNode readTree(String body) throws IOException{
        if(null == body){
            return  null ;
        }
        return OBJECT_MAPPER.readTree(body);
    }
}
