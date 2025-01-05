package com.literalura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> T convertirDatosJson(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
