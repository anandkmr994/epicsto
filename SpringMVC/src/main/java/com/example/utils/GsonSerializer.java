package com.example.utils;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by anand on 17/5/17.
 */
public class GsonSerializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GsonSerializer.class);
    
    private static final Gson gson = new Gson();

    private static final GsonSerializer INSTANCE = new GsonSerializer();

    public static GsonSerializer getInstance(){
        return INSTANCE ;
    }


    public String toJson(Object object){
        if(null == object){
            return  null ;
        }
        return gson.toJson(object);
    }

    public Object fromJson(String json, Class klass){
        if(null == json){
            return  null ;
        }
        return gson.fromJson(json,klass);
    }
}
