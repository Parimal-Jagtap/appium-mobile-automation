package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * TestDataReader — Reads test data from JSON files
 * Used for: user credentials, transaction data, account info
 */
public class TestDataReader {

    private static final String TEST_DATA_PATH = "test-data/mobile-test-data.json";
    private static JsonNode rootNode;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            rootNode = mapper.readTree(new File(TEST_DATA_PATH));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data: " + e.getMessage());
        }
    }

    public static String getString(String key) {
        String[] keys = key.split("\\.");
        JsonNode node = rootNode;
        for (String k : keys) {
            node = node.get(k);
            if (node == null) return null;
        }
        return node.asText();
    }
}
