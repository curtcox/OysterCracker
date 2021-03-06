package google;

import java.util.List;
import java.util.Map;

final class PlacesResponseParser
    extends JsonResponseParser
{
    Place construct(Map map) {
        Place place = new Place();
        place.name        = stringFrom(map,"name");
        place.address     = stringFrom(map,"formatted_address");
        place.vicinity    = stringFrom(map,"vicinity");
        place.id          = stringFrom(map,"place_id");
        place.reference   = stringFrom(map,"reference");
        Geometry geometry = Geometry.of(map);
        place.latitude    = geometry.latitude;
        place.longitude   = geometry.longitude;
        place.open_now    = null;
        place.icon        = uriFrom(map,"icon");
        place.price_level = doubleFrom(map,"price_level");
        place.rating      = doubleFrom(map,"rating");
        place.types       = (String[]) ((List) map.get("types")).toArray(new String[0]);
        return place;
    }
    
}
