package com.example.tmarestaurant.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.util.List;

public class MenuOriginConverter implements AttributeConverter<List<MenuOrigin>,String> {
    private  static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
    }


    @Override
    public String convertToDatabaseColumn(List<MenuOrigin> attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MenuOrigin> convertToEntityAttribute(String dbData) {
        try {
            return mapper.readValue(dbData, new TypeReference<List<MenuOrigin>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
