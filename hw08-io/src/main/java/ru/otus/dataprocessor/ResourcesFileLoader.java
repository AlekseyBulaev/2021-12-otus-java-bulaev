package ru.otus.dataprocessor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ru.otus.model.Measurement;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    private final ObjectMapper mapper = new ObjectMapper();

    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
        SimpleModule module =
                new SimpleModule("MeasurementDeserializer", new Version(3, 1, 8, null, null, null));
        module.addDeserializer(Measurement.class, new MeasurementDeserializer(Measurement.class));
        mapper.registerModule(module);
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        try (var io = getClass().getClassLoader().getResourceAsStream(fileName)) {
            return mapper.readValue(io, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new FileProcessException(e);
        }
    }
}
