package com.praxium.prepengine.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@UtilityClass
public class JsonUtils {

    private static final ObjectMapper mapper= new ObjectMapper().registerModule(new JavaTimeModule());

    // 1. Convert object to JSON string
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    // 2. Convert object to pretty JSON string
    public static String toPrettyJson(Object obj) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert object to pretty JSON", e);
        }
    }

    // 3. Convert JSON string to Java object
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON to object", e);
        }
    }

    // 4. Convert JSON string to Java object with generic type
    public static <T> T fromJson(String json, TypeReference<T> typeRef) {
        try {
            return mapper.readValue(json, typeRef);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON to generic type", e);
        }
    }

    // 5. Parse JSON string into JsonNode tree
    public static JsonNode parseTree(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON tree", e);
        }
    }

    // 6. Validate JSON format
    public static boolean isValidJson(String json) {
        try {
            mapper.readTree(json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // 7. Extract value from JSON by key
    public static String getStringValue(String json, String key) {
        try {
            JsonNode node = mapper.readTree(json);
            JsonNode valueNode = node.get(key);
            return valueNode != null ? valueNode.asText() : null;
        } catch (IOException e) {
            throw new RuntimeException("Failed to extract value from JSON", e);
        }
    }

    // 8. Convert JSON to Map
    public static Map<String, Object> toMap(String json) {
        return fromJson(json, new TypeReference<Map<String, Object>>() {
        });
    }

    // 9. Convert JSON to List<Map>
    public static List<Map<String, Object>> toList(String json) {
        return fromJson(json, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    // 10. Convert Map to JSON string
    public static String fromMap(Map<String, Object> map) {
        return toJson(map);
    }

    // 11. Minify JSON string
    public static String minifyJson(String json) {
        try {
            JsonNode node = mapper.readTree(json);
            return mapper.writeValueAsString(node);
        } catch (IOException e) {
            throw new RuntimeException("Failed to minify JSON", e);
        }
    }

    // 12. Merge two JSON strings (json2 overrides json1)
    public static String mergeJson(String json1, String json2) {
        try {
            ObjectNode node1 = (ObjectNode) mapper.readTree(json1);
            JsonNode node2 = mapper.readTree(json2);
            node1.setAll((ObjectNode) node2);
            return mapper.writeValueAsString(node1);
        } catch (IOException e) {
            throw new RuntimeException("Failed to merge JSON strings", e);
        }
    }

    // 13. Remove field from JSON
    public static String removeField(String json, String fieldName) {
        try {
            ObjectNode node = (ObjectNode) mapper.readTree(json);
            node.remove(fieldName);
            return mapper.writeValueAsString(node);
        } catch (IOException e) {
            throw new RuntimeException("Failed to remove field from JSON", e);
        }
    }

    // 14. Check if JSON contains a key
    public static boolean containsKey(String json, String key) {
        try {
            JsonNode node = mapper.readTree(json);
            return node.has(key);
        } catch (IOException e) {
            return false;
        }
    }

    // 15. Deep compare two JSON strings
    public static boolean deepEquals(String json1, String json2) {
        try {
            JsonNode node1 = mapper.readTree(json1);
            JsonNode node2 = mapper.readTree(json2);
            return node1.equals(node2);
        } catch (IOException e) {
            return false;
        }
    }
}
