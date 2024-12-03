Feature: Validating Place APIs

  @AddPlace
  Scenario Outline: Adding a Place by AddPlaceAPI
    Given Build base request specification
    When setting Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "post" http request
    Then user gets response with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

    When user gets "place_id" parameter value
    When user passes "place_id" query parameter
    And user calls "getPlaceAPI" with "get" http request
    Then "name" in response body is "<name>"


  Examples:
    |name|language|address|
    |Test1|English |Time Sq|
    |Test2|German |Berlin|

  @DeletePlace
  Scenario: Deleting a Place by DeletePlaceAPI
    Given Build base request specification
    When setting Delete Place Payload with "place_id"
    When user calls "deletePlaceAPI" with "post" http request
    Then user gets response with status code 200
    And "status" in response body is "OK"
