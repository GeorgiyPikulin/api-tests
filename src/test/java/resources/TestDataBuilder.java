package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.List;

public class TestDataBuilder {

    public static AddPlace setAddPlacePayLoad(String name, String language, String address) {
        return new AddPlace()
                .setLocation(
                        new Location().setLat(-38.383494).setLng(33.427362)
                )
                .setAccuracy(50)
                .setName(name)
                .setPhoneNumber("1234567")
                .setAddress(address)
                .setTypes(List.of("shoe park","shop"))
                .setWebsite("http://google.com")
                .setLanguage(language);
    }

    public static String setDeletePlacePayload(String parameter, String placeID) {
        return String.format("""
               {
                   "%s": "%s"
               }""",parameter, placeID);
    }
}
