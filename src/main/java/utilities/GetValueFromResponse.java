package utilities;

import io.restassured.path.json.JsonPath;

public class GetValueFromResponse {
    private static JsonPath parseToJson(String response) {
        return new JsonPath(response);
    }

   public static String getValueFromJson(String response, String parameter) {
        return parseToJson(response).getString(parameter);
   }
}
