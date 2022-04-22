package ru.otus.dataprocessor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.model.Measurement;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    private final ObjectMapper mapper = new ObjectMapper();

    private final URL fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = getClass().getClassLoader().getResource(fileName);
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        try {
            return mapper.readValue(fileName, new TypeReference<>(){});
        } catch (IOException e) {
            throw new RuntimeException("load() method finishes with IOException:= " + fileName);
        }
    }
}
