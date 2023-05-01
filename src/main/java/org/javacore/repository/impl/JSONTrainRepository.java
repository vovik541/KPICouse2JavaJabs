package org.javacore.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.javacore.entity.Train;
import org.javacore.repository.TrainRepository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.javacore.constant.Constants.STORAGE_FILE_NAME;
import static org.javacore.util.FilePathManager.getFilePath;

public class JSONTrainRepository implements TrainRepository {
    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());

    @Override
    public List<Train> getTrains() {
        String trainsJson;
        List<Train> trains = new LinkedList<>();

        try {
            trainsJson = new String(Files.readAllBytes(Paths.get(getFilePath(STORAGE_FILE_NAME))), StandardCharsets.UTF_8);
            trains = Arrays.asList(objectMapper.readValue(trainsJson, Train[].class));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return trains;
    }

    @Override
    public List<Train> saveTrains(List<Train> trains, String fileName) {
        String filePath = getFilePath(fileName);

        try {
            objectMapper.writeValue(new File(filePath), trains);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return trains;
    }
}
