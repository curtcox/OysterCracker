package com.mycompany.myapp.services;

import com.codename1.location.Location;
import com.codename1.location.LocationListener;
import com.codename1.location.LocationManager;
import com.mycompany.myapp.Registry;

/**
 *
 * @author Curt
 */
public final class Locations
    implements LocationListener
{

    private Location location;
    
    public Locations() {
        LocationManager manager = Registry.get(LocationManager.class);
        manager.setLocationListener(this);
    }

    public void locationUpdated(Location location) {
        this.location = location;
    }

    public void providerStateChanged(int newState) {}
    
    public static Locations of() {
        return Registry.get(Locations.class);
    }

    private LocationManager getManager() {
        return Registry.get(LocationManager.class);
    }

    public Location getCurrentLocation() {
        if (location!=null) {
            return location;
        }
        return getManager().getLastKnownLocation();
    }

    public double calculateDistance(Location a, Location b) {
        return DistanceCalculator.distance(a.getLatitude(),a.getLongitude(),b.getLatitude(),b.getLongitude());
    }

}
