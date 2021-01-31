package steps;

import io.cucumber.java.en.Given;
import utilities.APIUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class APISteps {

    @Given("user sends API call to {string} with data")
    public void user_sends_API_call_to_with_data(String apiName, io.cucumber.datatable.DataTable dataTable) throws IOException {
        APIUtils.parseYaml(apiName);
        String method = APIUtils.apiYaml.get("method").toString();

        Map<String, Object> data = dataTable.asMap(String.class,Object.class);

        if(method.equals("GET")) APIUtils.getCall(apiName, data );
        else if (method.equals("POST")) APIUtils.postCall(apiName, data);
        else if (method.equals("PUT")) APIUtils.putCall(apiName, data);
        else if (method.equals("DELETE")) APIUtils.deleteCall(apiName, data);

    }

}
