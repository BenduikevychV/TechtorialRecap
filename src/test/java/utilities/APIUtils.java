package utilities;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.yaml.snakeyaml.Yaml;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class APIUtils {

    public static Map<String, Object> apiYaml;
    public static RequestSpecification reqSpec;
    public static ResponseSpecification resSpec;
    public static Response response;
    public static String endpoint;
    public static DataStorage dataStorage = DataStorage.getInstance();
    public static Map<String, Object> responseData;

    public static Map<String, Object> parseYaml(String yamName) throws IOException {
        String path = "src/test/resources/config/api/" + yamName + ".yaml";

        InputStream input = null;
        Yaml yaml = new Yaml();
        try {
            input = new FileInputStream(path);
            apiYaml = yaml.load(input);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            input.close();
        }
        return apiYaml;
    }

    public static void apiConfig() throws IOException {
        reqSpec = given();
        reqSpec.baseUri(Config.getProperty("apiconfigs", "baseURI"))
                .basePath(Config.getProperty("apiconfigs", "basePath"))
                .contentType(apiYaml.get("content-type").toString());
        resSpec = given().accept(apiYaml.get("accept").toString()).response();
    }

    public static void getCall(String apiName, Map<String, Object> data) throws IOException {
        apiConfig();
        endpoint = apiYaml.get("endpoint").toString();
        setEndpoint(data);
        response = reqSpec
                .when().get(endpoint);
        response.then().statusCode(200);
        response.then().log().all();
        responseData = JsonFlattener.flattenAsMap(response.body().asString()); // string -> map
        dataStorage.jsonPayloads.put(apiName, responseData);


    }

    public static void postCall(String apiName, Map<String, Object> data) throws IOException {

        apiConfig();
        endpoint = apiYaml.get("endpoint").toString();
        setEndpoint(data);
        Map<String, Object> requestData = JsonParser.parseJson(apiName, data);
        String bodyStr = JsonUnflattener.unflatten(requestData.toString());
        response = reqSpec
                .when().body(bodyStr)
                .post(endpoint);
        response.then().statusCode(201);
        response.then().log().all();

        dataStorage.jsonPayloads.put(apiName,JsonFlattener.flattenAsMap(response.body().toString()));
    }

    public static void putCall(String apiName, Map<String, Object> data) throws IOException {

        apiConfig();
    }

    public static void deleteCall(String apiName, Map<String, Object> data) throws IOException {

        apiConfig();
    }

    public static void setEndpoint(Map<String, Object> data) {
        for(String key : data.keySet()){
            if (key.equals(":param")){
                endpoint = endpoint.replace("$",data.get(key).toString());
            }
        }
    }
}
