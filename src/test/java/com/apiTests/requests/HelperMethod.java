package com.apiTests.requests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelperMethod {

    public static String data;
    public static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(HelperMethod.class);

    public static String requestBodyLoader(String dataPath) {

        try {
            // The "userDataPath" string variable provides a path, and the JSON object within this path
            // is stored in the "userLoginData" variable.
            data = new String(Files.readAllBytes(Paths.get(dataPath)));
        } catch (IOException e) {
            logger.error("The user information in the 'dataPath' JSON file was not assigned to a string variable named 'data'{}", e.getMessage());
        }
        return data;
    }

    public static void writeStringToJsonFile(String jsonString, String dataPath) {

        try {
            // Parse the JSON string as a "JsonNode"
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // Write the JSON data to a file.
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(dataPath), jsonNode);

            logger.info("The JSON data was successfully written to the file: {}", dataPath);

        } catch (Exception e) {
            logger.error("Error writing to the file: {}", e.getMessage());
        }
    }

    public static void writeStringToFile(String content, String filePath) {
        try {
            Files.write(Paths.get(filePath), content.getBytes());
            logger.info("The content was successfully written to the file: {}", filePath);
        } catch (IOException e) {
            logger.error("Error writing to the file: {}", e.getMessage());
        }
    }

    public static String createJson(String username, String password) {
        return String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password);
    }
}