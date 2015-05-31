package c1.screenfactories;

import com.codename1.ui.Label;
import java.util.ArrayList;
import java.util.List;
import common.domain.Type;
import c1.event.LiveList;
import c1.event.SimpleLiveList;
import common.screen.dynamic.GlobScreenFactory;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.screenparts.TypeListCellConfigurer;
import common.screenparts.TypeTextFilter;
import common.screens.FilterScreen;
import c1.uilist.C1SearchFilterInstaller;
import c1.uilist.C1SearchableList;

public final class ServiceProviderFilterScreenFactory {

    public static ScreenFactory FACTORY = GlobScreenFactory.filter("Filter", new ScreenFactory() {
        @Override
        public Screen[] create(ScreenLink link) {
            return new Screen[]{new FilterScreen(link, newSearchableList())};
        }
    });

    private static LiveList<Type> getTypes() {
        List<Type> list = new ArrayList<Type>();
        for (String name : providerTypeNames()) {
            list.add(new Type(name));
        }
        return new SimpleLiveList(list);
    }

    private static C1SearchableList<Type> newSearchableList() {
        C1SearchableList list = new C1SearchableList(getTypes(),new Label(),new TypeListCellConfigurer());
        C1SearchFilterInstaller.install(list, new TypeTextFilter());
        return list;
    }

    private static String[] providerTypeNames() {
        return new String[]{
            "accounting", "airport", "amusement_park", "aquarium", "art_gallery", "atm", "bakery", "bank", "bar",
            "beauty_salon", "bicycle_store", "book_store", "bowling_alley", "bus_station", "cafe", "campground",
            "car_dealer", "car_rental", "car_repair", "car_wash", "casino", "cemetery", "church", "city_hall",
            "clothing_store", "convenience_store", "courthouse", "dentist", "department_store", "doctor", "electrician",
            "electronics_store", "embassy", "establishment", "finance", "fire_station", "florist", "food", "funeral_home",
            "furniture_store", "gas_station", "general_contractor", "grocery_or_supermarket", "gym", "hair_care",
            "hardware_store", "health", "hindu_temple", "home_goods_store", "hospital", "insurance_agency", "jewelry_store",
            "laundry", "lawyer", "library", "liquor_store", "local_government_office", "locksmith", "lodging",
            "meal_delivery", "meal_takeaway", "mosque", "movie_rental", "movie_theater", "moving_company", "museum",
            "night_club", "painter", "park", "parking", "pet_store", "pharmacy", "physiotherapist", "place_of_worship",
            "plumber", "police", "post_office", "real_estate_agency", "restaurant", "roofing_contractor", "rv_park",
            "school", "shoe_store", "shopping_mall", "spa", "stadium", "storage", "store", "subway_station", "synagogue",
            "taxi_stand", "train_station", "travel_agency", "university", "veterinary_care", "zoo"
        };
    }

}
