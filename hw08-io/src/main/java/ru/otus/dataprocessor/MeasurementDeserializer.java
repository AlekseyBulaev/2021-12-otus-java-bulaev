package ru.otus.dataprocessor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.otus.model.Measurement;

import java.io.IOException;

public class MeasurementDeserializer extends StdDeserializer<Measurement> {
    @Override
    public Measurement deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String name = null;
        double value = 0.0;
        while(!p.isClosed()){
            JsonToken jsonToken = p.nextToken();

            if(JsonToken.END_OBJECT.equals(jsonToken)){
                return new Measurement(name, value);
            }

            if(JsonToken.FIELD_NAME.equals(jsonToken)){
                String fieldName = p.getCurrentName();
                System.out.println(fieldName);
                jsonToken = p.nextToken();
                if("name".equals(fieldName)){
                    name = p.getValueAsString();
                } else if ("value".equals(fieldName)){
                    value = p.getValueAsDouble();
                }
            }
        }
        return null;
    }

    public MeasurementDeserializer(Class<Measurement> vc) {
        super(vc);
    }
}
