package org.fabri1983.menuapp.protocol.parserutil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalTime;

public class CustomLocalTimeDeserializer extends JsonDeserializer<LocalTime> {
	
    @Override
    public LocalTime deserialize(JsonParser source, DeserializationContext arg1) throws IOException, JsonProcessingException {
        return LocalTime.parse(source.getText());
    }
}