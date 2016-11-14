package org.fabri1983.menuapp.core.presentation.parserutil;

import java.io.IOException;
import java.time.LocalTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomLocalTimeDeserializer extends JsonDeserializer<LocalTime> {
	
    @Override
    public LocalTime deserialize(JsonParser source, DeserializationContext arg1) throws IOException, JsonProcessingException {
        return LocalTime.parse(source.getText());
    }
}