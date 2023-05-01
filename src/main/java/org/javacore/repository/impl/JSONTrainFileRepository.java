package org.javacore.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.javacore.entity.Train;
import org.javacore.repository.TrainFileRepository;

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

public class JSONTrainFileRepository implements TrainFileRepository {
    private static Logger logger = LogManager.getLogger(JSONTrainFileRepository.class);
    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());

    public JSONTrainFileRepository() {
        logger.debug("JSONTrainFileRepository created");
    }

    @Override
    public List<Train> getTrains() {
        String trainsJson;
        List<Train> trains = new LinkedList<>();

        try {
            trainsJson = new String(Files.readAllBytes(Paths.get(getFilePath(STORAGE_FILE_NAME))), StandardCharsets.UTF_8);
            trains = Arrays.asList(objectMapper.readValue(trainsJson, Train[].class));
            logger.info("All trains obtained from store");
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }

        return trains;
    }

    @Override
    public List<Train> saveTrains(List<Train> trains, String fileName) {
        String filePath = getFilePath(fileName);

        try {
            objectMapper.writeValue(new File(filePath), trains);
        } catch (IOException ex) {
            logger.warn("Not saved the result " + ex.getMessage());
            return new LinkedList<>();
        }
        logger.info("Result was saved to file: " + filePath);

        return trains;
    }
}
