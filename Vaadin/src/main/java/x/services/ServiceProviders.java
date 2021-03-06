package x.services;

import google.Place;
import google.PlacesSearch;
import x.Registry;
import x.domain.*;
import x.stores.MyRatings;

import java.util.ArrayList;
import java.util.List;

public final class ServiceProviders {
    
    private final PlacesSearch places = new PlacesSearch();
    
    public static ServiceProviders of() {
        return Registry.get(ServiceProviders.class);
    }
    
    public List<ServiceProvider> nearby(Type[] types, int radius) {
        List<ServiceProvider> providers = new ArrayList<ServiceProvider>();
        for (Place place : placesNearHere(types,radius)) {
            providers.add(serviceProviderFromPlace(place));
        }
        return providers;
    }

    private List<Place> placesNearHere(Type[] types, int radius) {
        LocationReading currentLocation = locations().getCurrentLocation();
        if (currentLocation==null) {
            return new ArrayList<Place>();
        }
        double latitude = currentLocation.latitude;
        double longitude = currentLocation.longitude;
        return places.nearbySearch(latitude, longitude, radius,asStrings(types));
    }

    private LocationService locations() {
        return Registry.get(LocationService.class);
    }

    private String[] asStrings(Type[] types) {
        List<String> list = new ArrayList();
        for (Type type : types) {
            list.add(type.toString());
        }
        return list.toArray(new String[0]);
    }
    
    private ServiceProvider serviceProviderFromPlace(Place place) {
        ID id = new ID(place.id);
        return new ServiceProvider(
            id, new Name(place.name), locationFromPlace(place),
            new Address(place.vicinity),
            place.price_level,place.rating,typesFromPlace(place),place.icon,
            MyRatings.of().getFor(id)
        );
    }

    private LocationReading locationFromPlace(Place place) {
        return new LocationReading(place.latitude,place.longitude);
    }

    private Type[] typesFromPlace(Place place) {
        List<Type> list = new ArrayList();
        for (String type : place.types) {
            list.add(new Type(type));
        }
        return list.toArray(new Type[0]);
    }
}
