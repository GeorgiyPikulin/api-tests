package resources;

public enum ApiResourcesEnums {

    addPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");

    private final String resource;

    ApiResourcesEnums(String resource) {
        this.resource = resource;
    }
    public String getResource() {
        return resource;
    }
}
