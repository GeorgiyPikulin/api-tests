package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    private static RequestSpecification requestSpecification;

    private String getPropertyValue(String propertyName) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/java/resources/global.properties"));
        return properties.getProperty(propertyName);
    }

    public RequestSpecification buildRequestSpecification() throws IOException {

        if (requestSpecification==null) {
            PrintStream logFile = new PrintStream(new FileOutputStream("logging.txt"));

            requestSpecification = new RequestSpecBuilder()
                    .setBaseUri(getPropertyValue("baseUrl"))
                    .addQueryParam("key","qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(logFile))
                    .addFilter(ResponseLoggingFilter.logResponseTo(logFile))
                    .setContentType(ContentType.JSON)
                    .build();
            return requestSpecification;
        }
        return requestSpecification;
    }
}
