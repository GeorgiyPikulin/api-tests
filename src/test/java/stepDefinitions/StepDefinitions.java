package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.ApiResourcesEnums;
import resources.TestDataBuilder;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import static utilities.GetValueFromResponse.getValueFromJson;

public class StepDefinitions {

    private final Utils utils;
    private RequestSpecification builtRequestSpecification;
    private RequestSpecification request;
    private Response response;
    static String parameterValue;

    public StepDefinitions() {
        this.utils = new Utils();
    }

    @Given("Build base request specification")
    public void build_base_request_specification() throws IOException {
        builtRequestSpecification = given().spec(utils.buildRequestSpecification());
    }

    @When("setting Add Place Payload with {string} {string} {string}")
    public void setting_add_place_payload_with(String name, String language, String address) {
        request = builtRequestSpecification.body(TestDataBuilder.setAddPlacePayLoad(name, language, address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_endpoint_with_http_request(String resource, String httpMethod) {
        String apiResource = ApiResourcesEnums.valueOf(resource).getResource();

        switch (httpMethod.toUpperCase()) {
            case "POST" -> response = request.when().post(apiResource);
            case "GET" -> response = request.when().get(apiResource);
            default -> throw new IllegalArgumentException("Unsupported HTTP method: " + httpMethod);
        }
    }

    @Then("user gets response with status code {int}")
    public void user_gets_response_with_status_code(int statusCode) {
        assertEquals(statusCode,response.getStatusCode());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String parameterName, String expectedValue) {
        assertEquals(expectedValue, getValueFromJson(response.asString(),parameterName));
    }

    @When("user gets {string} parameter value")
    public void user_gets_parameter_value(String parameterName) {
        parameterValue = getValueFromJson(response.asString(),parameterName);
    }

    @When("user passes {string} query parameter")
    public void user_passes_query_parameter(String queryParameter) {
        request = builtRequestSpecification.queryParam(queryParameter, parameterValue);
    }

    @When("setting Delete Place Payload with {string}")
    public void setting_delete_place_payload_with(String parameter) {
        request = builtRequestSpecification.body(TestDataBuilder.setDeletePlacePayload(parameter,parameterValue));
    }
}
