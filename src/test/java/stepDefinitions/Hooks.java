package stepDefinitions;

import io.cucumber.java.Before;
import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        if (StepDefinitions.parameterValue == null) {
            StepDefinitions stepDefinitions = new StepDefinitions();
            stepDefinitions.build_base_request_specification();
            stepDefinitions.setting_add_place_payload_with(
                   "Test name", "Test lang", "Test address");
            stepDefinitions.user_calls_endpoint_with_http_request("addPlaceAPI","post");
            stepDefinitions.user_gets_parameter_value("place_id");
        }
    }
}
