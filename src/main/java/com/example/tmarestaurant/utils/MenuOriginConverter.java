package com.example.tmarestaurant.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.util.List;
import java.util.Set;

public class MenuOriginConverter implements AttributeConverter<Set<MenuOrigin>,String> {
    private  static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(Set<MenuOrigin> attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<MenuOrigin> convertToEntityAttribute(String dbData) {
        try {
            return mapper.readValue(dbData, new TypeReference<Set<MenuOrigin>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
