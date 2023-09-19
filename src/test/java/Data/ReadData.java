package Data;

import io.restassured.path.json.JsonPath;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReadData {
    private String jsonFilePath;
    private FileReader reader;

    public ReadData(String jsonFilePath) throws FileNotFoundException {
        this.jsonFilePath = jsonFilePath;

        reader = new FileReader(jsonFilePath);
    }

    public String getTestData(String jsonPath) throws FileNotFoundException {
        Object testData;

        reader = new FileReader(jsonFilePath);

        testData = JsonPath.from(reader).getString(jsonPath);

        return String.valueOf(testData);
    }
}