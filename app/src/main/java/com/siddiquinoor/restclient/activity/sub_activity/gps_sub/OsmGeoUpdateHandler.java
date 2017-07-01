package com.siddiquinoor.restclient.activity.sub_activity.gps_sub;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;

/**
 * Created by Faisal on 6/21/2016.
 */
public class OsmGeoUpdateHandler implements LocationListener {
    private MapActivity mMapActivity;

    public OsmGeoUpdateHandler(MapActivity mMapActivity) {
        this.mMapActivity = mMapActivity;
    }

    @Override
    public void onLocationChanged(Location location) {

        Toast.makeText(mMapActivity,
                "latitude = " + location.getLatitude() * 1e6 + " longitude = " + location.getLongitude() * 1e6,
                Toast.LENGTH_SHORT).show();

        int latitude = (int) (location.getLatitude() * 1E6);
        int longitude = (int) (location.getLongitude() * 1E6);
        GeoPoint point = new GeoPoint(latitude, longitude);
       // mMapActivity.updateCarPosition(point);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
