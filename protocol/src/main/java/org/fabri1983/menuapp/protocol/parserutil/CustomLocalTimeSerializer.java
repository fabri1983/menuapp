package org.fabri1983.menuapp.protocol.parserutil;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalTime;

public class CustomLocalTimeSerializer extends JsonSerializer<LocalTime> {
	
    @Override
    public void serialize(LocalTime source, JsonGenerator target, SerializerProvider arg2) throws IOException, JsonProcessingException {
        target.writeString(source.toString());
    }
}
