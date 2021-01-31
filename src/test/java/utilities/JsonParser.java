package utilities;

import com.github.wnameless.json.flattener.JsonFlattener;
import io.cucumber.java.it.Ma;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class JsonParser {

    public static Map<String, Object> flattenJson = null;

    public static Map<String, Object> parseJson(String apiName, Map<String, Object> data){

        String path = APIUtils.apiYaml.get("body").toString();
        JSONParser jsonParser = new JSONParser();

        try {

        FileReader reader = new FileReader(path);
        Object obj = jsonParser.parse(reader);
        flattenJson = JsonFlattener.flattenAsMap(obj.toString());

        }catch (IOException |ParseException e){
            e.getStackTrace();
        }

        for (String key : data.keySet()){
            flattenJson.put(key,data.get(key));
        }
        return flattenJson;
    }
}
