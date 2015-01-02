package c1.screens;

import java.net.URI;
import java.util.Arrays;

import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.screen.dynamic.GlobScreenFactory;
import common.ui.UIButton;
import common.ui.UIComponent;
import common.ui.UILabel;
import common.ui.UITable;
import common.domain.ServiceProvider;
import common.domain.Type;
import c1.screenparts.ProviderRatingButton;
import c1.ui.LinkButton;

/**
 * For showing details about a particular provider.
 * @author Curt
 */
public final class ProviderDetailsScreen
    extends Screen
{
    private final UILabel name = new UILabel();
    private final UILabel distance = new UILabel();
    private final UILabel types = new UILabel();
    private final UILabel price = new UILabel();
    private final UILabel rating = new UILabel();
    private final UILabel vicinity = new UILabel();
    private final UIButton icon = new LinkButton("",new SearchLinkFactory());
    
    private final class SearchLinkFactory implements ScreenLink.Factory {
        public ScreenLink create() {
            return new ScreenLink("Search",getType());
        }
    }

    public static ScreenFactory FACTORY = new GlobScreenFactory("ProviderDetails") {
        public Screen doCreate(ScreenLink link) {
            return new ProviderDetailsScreen();
        }     
    };

    ProviderDetailsScreen() {
        super("Provider Details");
    }
    
    @Override
    public UIComponent layoutForPortrait() {
        return new UITable(8,1,
                    name,
                    distance,
                    vicinity,
                    price,
                    rating,
                    icon,
                    types,
                    ProviderRatingButton.of()
        );
    }
    
    @Override
    public void refresh() {
        updateName();
        updateRating();
        updatePrice();
        updateIcon();
        updateTypes();
        updateDistance();
        updateVicinity();
        super.refresh();
    }

    private void updateName() {
        name.text = provider().name.toString();
    }

    private void updateRating() {
        this.rating.text = getRatingText();
    }

    private void updatePrice() {
        price.text= getPriceText();
    }

    private Type[] getType() {
        return new Type[] {provider().types[0]};
    }
    
    private String getRatingText() {
        return provider().rating == null ? "No rating information" : "Rating : " + provider().rating;
    }

    private String getPriceText() {
        return provider().priceLevel == null ? "No price information" : "Price Level : " + provider().priceLevel;
    }

    private void updateIcon() {
        URI uri = provider().icon;
        if (uri!=null) {
            icon.icon = uri.toString();
        }
    }
    
    private void updateTypes() {
        types.text = Arrays.asList(provider().types).toString();
    }

    private void updateDistance() {
        distance.text = provider().distanceFromCurrentLocation();
    }

    private void updateVicinity() {
        vicinity.text=provider().address.toString();
    }

    private ServiceProvider provider() {
        return ServiceProvider.getSelected();
    }
}