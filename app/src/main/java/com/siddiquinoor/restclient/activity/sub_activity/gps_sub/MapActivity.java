package com.siddiquinoor.restclient.activity.sub_activity.gps_sub;


import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.GPSLocationSearchPage;
import com.siddiquinoor.restclient.data_model.GPS_LocationDataModel;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.GPSLocationLatLong;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.infowindow.InfoWindow;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class MapActivity extends BaseActivity {
    private static final String TAG = "MapActivity";

    MapView mMapView;
    IMapController mapViewController;



    private ArrayList<OverlayItem> anotherOverlayItemArray;
    MyLocationNewOverlay myLocationNewOverlay = null;


    double longitude = 0;
    double latitude = 0;
    GpsMyLocationProvider locationProvider;
    private ScaleBarOverlay mScaleBarOverlay;


    private Context context = MapActivity.this;
    private Button btnSave;
    private Button btnBackToLocSEARCH;
    private TextView tvLat;
    private TextView tvLong, tvGroupName, tvSubGroupName, tv_exitLong, tv_exitLat;

   private LocationManager mLManager;
    private GPS_LocationDataModel gpsData;
    private SQLiteHandler sqlH;
    private Spinner spLocation;
    private String idLocation;
    private String strLocation;
    private String idCountry;
    private boolean locationSelected = false;

    private ImageButton ibtnSetAttributes, ibtnSetNearBy;
    private Context mContext;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        /**
         *
         */
        initialize();

        Intent intent = getIntent();
        idCountry = intent.getExtras().getString(KEY.COUNTRY_ID);
        String dir = intent.getStringExtra(KEY.DIR_CLASS_NAME_KEY);
        if (dir.equals("SearchSubGroup")) {
            idLocation = intent.getStringExtra(KEY.LOCATION_CODE);
            strLocation = intent.getStringExtra(KEY.LOCATION_NAME);
        } else if (dir.equals("GPSActivity")) {
            /**
             * to get previous state
             */

        /*    idLocation = intent.getExtras().getString(KEY.LOCATION_CODE);
            strLocation = intent.getExtras().getString(KEY.LOCATION_NAME);*/

            GPS_LocationDataModel gpsData = intent.getExtras().getParcelable(KEY.GPS_DATA_OBJECT_KEY);
            if (gpsData != null) {
                idCountry = gpsData.getAdmCountryCode();
            }


        }

        addMapScaleBar();

        setAttributes();
        saveCoordinate();

        mLManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //for demo, getLastKnownLocation from GPS only, not from NETWORK
        Location lastLocation = mLManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastLocation != null) {
            /**
             * If gps is on than for first time
             */
            updateLoc(lastLocation, true);
        }


        goHome();

        loadLocation(idCountry);

        setNearByListener();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //   client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }// end of onCreate

    private void setNearByListener() {
        ibtnSetNearBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showNearBySelectDialog();

            }
        });
    }


    /**
     * <p>This Method Create A Custom Dialog .Which will show the List of The Sub Group </p>
     */

    private void showNearBySelectDialog() {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MapActivity.this);

        builderSingle.setTitle("Select a SubGroup");


        String criteria = SQLiteQuery.showNearBy_gpsSubGroup_sql(idCountry);
        List<SpinnerHelper> listOfSubGroup = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, null, false);

        listOfSubGroup.remove(0);
        final ArrayAdapter<SpinnerHelper> arrayAdapter = new ArrayAdapter<SpinnerHelper>(MapActivity.this, android.R.layout.select_dialog_singlechoice, listOfSubGroup);

        builderSingle.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(
                arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String strName = arrayAdapter.getItem(which).getValue();
                        final String temGroupCode = arrayAdapter.getItem(which).getId().substring(0, 3);
                        final String temSubGroupCode = arrayAdapter.getItem(which).getId().substring(3);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(
                                MapActivity.this);
                        builderInner.setMessage(strName);
                        builderInner.setTitle("Selected Sub Group is");
                        builderInner.setPositiveButton(
                                "Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        dialog.dismiss();
                                        /**                                         * here set the plot                                         */
                                        setNearByCoordinates(idCountry, temGroupCode, temSubGroupCode);
                                    }
                                });
                        builderInner.setNegativeButton(
                                "Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        dialog.dismiss();

                                    }
                                });
                        builderInner.show();
                    }
                });
        builderSingle.show();
    }

    /**
     * We introduce  select the sub group column  is must bucause
     * with out selecting the sub grtoup user can easy access  the
     * set attributes (GPS Activity )class  and with out sub group
     * no lang and lat value available on the page so user will easyly confuse
     * to  click necessary action(Image need all code) button s
     */
    private void setAttributes() {
        ibtnSetAttributes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idLocation != null) {
                    if (idLocation.length() > 2) {
                        String subGrpName = tvSubGroupName.getText().toString();

                        if (subGrpName.length() > 0 && !subGrpName.equals("")) {
                            goToGpsModule();
                        } else {
                            ADNotificationManager dialog = new ADNotificationManager();
                            dialog.showAlertDialog(context, "Missing", "SubGroup missing ...", true);
                        }


                    }

                } else {
                    ADNotificationManager dalog = new ADNotificationManager();
                    dalog.showErrorDialog(MapActivity.this, "Select location.");
                }


            }
        });
    }

    /**
     * @param cCode        Country Code
     * @param groupCode    Gps Location Group Code
     * @param subGroupCode Gps Location Sub GroupCode
     *                     <p> This method ploit the Coordinate near by location </p>
     * @since 02040215
     */

    private void setNearByCoordinates(final String cCode, final String groupCode, final String subGroupCode) {

        ArrayList<GPS_LocationDataModel> listOfGpsData = sqlH.getSubGroupSpecificLatLongCoordinates(cCode, groupCode, subGroupCode);
        /**
         * clear the previous marker
         */
        clearMapScreen();

        InfoWindow.closeAllInfoWindowsOn(mMapView);

        Location lastLocation = mLManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastLocation != null) {
            GeoPoint locGeoPoint = new GeoPoint(lastLocation.getLatitude(), lastLocation.getLongitude());
            mapViewController.setCenter(locGeoPoint);
            mapViewController.setZoom(8);
            for (int i = 0; i < listOfGpsData.size(); i++) {
                GPS_LocationDataModel data = listOfGpsData.get(i);

                if (calculateDistanceBetween2Point(lastLocation.getLatitude(), lastLocation.getLongitude(), Double.valueOf(data.getLat()), Double.valueOf(data.getLng())) < 500)
                    setMarker(data.getLocationName(), Double.valueOf(data.getLat()), Double.valueOf(data.getLng()));


            }
        }


    }

    private void clearMapScreen() {
        mMapView.getOverlays().clear();
    }

    /**
     * @param startLat  gps lat
     * @param startLong gps lon
     * @param endLat    exti lat
     * @param endLong   exit long
     * @return distance in KM
     * @since 02040215
     * <p>Distance, bearing and more between Latitude/Longitude points</p>
     * <p> R is earth’s radius (mean radius = 6,371km)</p>
     * <p/>
     * <p>var lat1Red = lat1.toRadians();</p>
     * <p>var lat2Red = lat2.toRadians();</p>
     * <p>var delLat = (lat2 - lat1).toRadians();</p>
     * <p>var delLong = (lon2 - lon1).toRadians();</p>
     * <p>var a = Math.sin(delLat / 2) * Math.sin(delLat / 2) +</p>
     * <p> Math.cos(delLat) * Math.cos(lat2Red) *</p>
     * <p> Math.sin(delLong / 2) * Math.sin(delLong / 2);</p>
     * <p> var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));</p>
     * <p>  var d = R * c;</p>
     * <p/>
     * *
     */

    public double calculateDistanceBetween2Point(double startLat, double startLong, double endLat, double endLong) {
        /**         *  R is earths radius (mean radius = 6,371km)         */
        double earthR = 6371e3;
        double dagToRad = Math.PI / 180;

        double radStartLat;
        double radStartLong;
        double radEndLat;
        double radEndLong;

        double distance = 0;

        double dalLat;
        double dalLong;


        radStartLat = startLat * dagToRad;
        radStartLong = startLong * dagToRad;
        radEndLat = endLat * dagToRad;
        radEndLong = endLong * dagToRad;

        dalLat = radEndLat - radStartLat;
        dalLong = radEndLong - radStartLong;

        double a = Math.sin(dalLat / 2) * Math.sin(dalLat / 2) + Math.cos(radStartLat) * Math.cos(radEndLat) *
                Math.sin(dalLong / 2) * Math.sin(dalLong / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        distance = earthR * c;

        // distance=distance/1000;
        Log.d("DIS", "distance:" + distance);
        return distance;
    }


    /**
     * @param loc      Location object
     * @param fistTime detect flag
     */

    private void updateLoc(Location loc, boolean fistTime) {
        GeoPoint locGeoPoint = new GeoPoint(loc.getLatitude(), loc.getLongitude());
        if (fistTime)
            mapViewController.setCenter(locGeoPoint);

        Marker startMarker = new Marker(mMapView);
        startMarker.setPosition(locGeoPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mMapView.getOverlays().add(startMarker);
        changeCurrentGpsLocationIcon(startMarker);


        /**
         * to update text view
         */
        tvLat.setText(String.valueOf(loc.getLatitude()));
        tvLong.setText(String.valueOf(loc.getLongitude()));
    }

    /**
     * This method Change the Icon of Current location
     *
     * @param marker Overbear Marker
     */

    private void changeCurrentGpsLocationIcon(Marker marker) {
        /**
         * change icon
         */
        marker.setIcon(getResources().getDrawable(R.drawable.gps));
        marker.setTitle("Current Location");
        mMapView.invalidate();
    }

    /**
     *
     */
    private void saveCoordinate() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();

            }
        });
    }

    private void saveData() {
        /**
         * A validation if location is selected
         */
        if (locationSelected) {
            /**
             * if the gps off than if condition is true
             */
            if (tvLat.getText().toString().equals("00.00") || tvLong.getText().toString().equals("00.00")) {
                gpsSettingShowDialog();
            } else {

                String subGrpName = tvSubGroupName.getText().toString();
                /**
                 * We introduce  select the sub group column  is must because
                 * with out selecting the sub grtoup user can easy access  the
                 * set attributes (GPS Activity )class  and with out sub group
                 * no lang and lat value available on the page so user will easyly confuse
                 * to  click necessary action(Image need all code) button s
                 */
                if (subGrpName.length() > 0 && !subGrpName.equals("")) {

                    double latitude = Double.valueOf(tvLat.getText().toString());
                    double longitude = Double.valueOf(tvLong.getText().toString());


                    String entryBy = "";
                    String entryDate = "";

                    try {
                        entryBy = getStaffID();
                        entryDate = getDateTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    gpsData.setLat(String.valueOf(latitude));
                    gpsData.setLng(String.valueOf(longitude));
                    gpsData.setEntryBy(entryBy);
                    gpsData.setEntryDate(entryDate);
                   //                    * set marker in map here in save                     */
                    initializeMap();

                    setMarker(gpsData.getLocationName(), latitude, longitude);

                    //                     * for local data base and Syntax for  server                      */
                    sqlH.updateGpsLocation(gpsData.getAdmCountryCode(), gpsData.getGroupCode(), gpsData.getSubGroupCode(), gpsData.getLocationCode(), gpsData.getLat(), gpsData.getLng(), entryBy, entryDate);

                    Toast.makeText(context, "save successfully", Toast.LENGTH_SHORT).show();

                    GPSLocationLatLong data = sqlH.getLocationSpecificLatLong(idCountry, gpsData.getGroupCode(), gpsData.getSubGroupCode(), gpsData.getLocationCode());
                 //                  * if data exits in data base                     */

                    tv_exitLat.setText(data.getLatitude());
                    tv_exitLong.setText(data.getLongitude());


                } else {
                    ADNotificationManager dialog = new ADNotificationManager();
                    dialog.showAlertDialog(context, "Missing", "SubGroup missing \nSave deny ", true);
                }


            }


        } else {
            ADNotificationManager dialog = new ADNotificationManager();
            dialog.showErrorDialog(MapActivity.this, "Select Location");
        }

    }

    /**
     * This Brings GPS Setting If the GPS is off
     */

    private void gpsSettingShowDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder
                .setMessage("Location service is disabled in this device")
                .setCancelable(false)
                .setPositiveButton("Enable",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                /** some problempatic*/
                                turnGPSOn();
                                Intent callGPSSettingIntent = new Intent(
                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                context.startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void initialize() {
        viewReference();
        initializeMap();
        mContext = MapActivity.this;
        locationProvider = new GpsMyLocationProvider(mContext);
        sqlH = new SQLiteHandler(this);


    }

    public void turnGPSOn() {


        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    /*    Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", true);
        this.context.sendBroadcast(intent);

        String provider = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (!provider.contains("gps")) { //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            this.context.sendBroadcast(poke);


        }*/
    }

    private void viewReference() {
        btnBackToLocSEARCH = (Button) findViewById(R.id.btnRegisterFooter);
        btnSave = (Button) findViewById(R.id.btnHomeFooter);
        tvLat = (TextView) findViewById(R.id.tv_latval);
        tvLong = (TextView) findViewById(R.id.tv_longval);
        tv_exitLong = (TextView) findViewById(R.id.tv_exit_longval);
        tv_exitLat = (TextView) findViewById(R.id.tv_exit_latval);
        tvGroupName = (TextView) findViewById(R.id.tv_GroupName);
        tvSubGroupName = (TextView) findViewById(R.id.tv_SubGroupName);
        spLocation = (Spinner) findViewById(R.id.spMap_location);
        ibtnSetAttributes = (ImageButton) findViewById(R.id.ibtn_setAttributes);
        ibtnSetNearBy = (ImageButton) findViewById(R.id.ibtn_setNearby);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpSaveButton() {
        btnSave.setText("");
        Drawable saveImage = getResources().getDrawable(R.drawable.save_b);
        btnSave.setCompoundDrawablesRelativeWithIntrinsicBounds(saveImage, null, null, null);
        setPaddingButton(MapActivity.this, saveImage, btnSave);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpGpsButton() {
        btnBackToLocSEARCH.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.goto_back);
        btnBackToLocSEARCH.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(MapActivity.this, imageHome, btnBackToLocSEARCH);
    }


    /**
     * calling getWidth() and getHeight() too early:
     * When  the UI has not been sized and laid out on the screen yet..
     *
     * @param hasFocus the value will be true when UI is focus
     */

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        setUpSaveButton();
        setUpGpsButton();
    }


    private void goHome() {
        btnBackToLocSEARCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent ibackSearch = new Intent(context, GPSLocationSearchPage.class);

                ibackSearch.putExtra(KEY.COUNTRY_ID, idCountry);
                ibackSearch.putExtra(KEY.DIR_CLASS_NAME_KEY, "MapActivity");

                startActivity(ibackSearch);
            }
        });
    }

    private void addMapScaleBar() {

        DisplayMetrics dm;
        dm = getDevicePixels();


        mScaleBarOverlay = new ScaleBarOverlay(mMapView);
        mScaleBarOverlay.setCentred(true);
        /**
         * play around with these values to get the location on screen in the right place for your application
         */

        mScaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        mMapView.getOverlays().add(this.mScaleBarOverlay);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "in onResume method");
        Log.d(TAG, "id Country : id " + idCountry);

        //   if (ContextCompat..c.checkSelfPermission(MapActivity.this, Manifest.permission.WRITE_CALENDAR))
        mLManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
//        mLManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);


    }

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateLoc(location, false);
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
    };


    /**
     * @return dm
     */

    private DisplayMetrics getDevicePixels() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        return dm;
    }

    /**
     * <p>In this method View And All the </p>
     */

    private void initializeMap() {
        mMapView = (MapView) findViewById(R.id.mapView);
        mapViewController = mMapView.getController();
        mMapView.setClickable(true);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        mMapView.setUseDataConnection(true);
        mMapView.setTileSource(TileSourceFactory.HIKEBIKEMAP);
        mapViewController.setCenter(new GeoPoint(-14.9805, 34.9559));
        mapViewController.setZoom(8);
        InfoWindow.closeAllInfoWindowsOn(mMapView);
    }


    private boolean isLongPress = false;
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> myOnItemGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
        @Override
        public boolean onItemSingleTapUp(int index, OverlayItem item) {

            isLongPress = false;
            return true;
        }

        @Override
        public boolean onItemLongPress(int index, OverlayItem item) {

            isLongPress = true;
            Toast.makeText(MapActivity.this, item.getTitle() + "\n" + item.getSnippet() + "\n" + item.getPoint().getLatitudeE6() + " : " + item.getPoint().getLongitudeE6(), Toast.LENGTH_LONG).show();

            return true;
        }
    };

    private void goToGpsModule() {

        ADNotificationManager dialog = new ADNotificationManager();

        String msg = "Group : " + gpsData.getGroupName()
                + "\nSubGroup : " + gpsData.getSubGroupName()
                + "\nLocation : " + gpsData.getLocationName();


        /**
         * pass the parameter in GPS module
         * */


        Intent iGpsPage = new Intent(MapActivity.this, PointAttributes.class);
        iGpsPage.putExtra(KEY.DIR_CLASS_NAME_KEY, "MapActivity");
        iGpsPage.putExtra(KEY.LATITUDE, tvLat.getText().toString());
        iGpsPage.putExtra(KEY.LONGITUDE, tvLong.getText().toString());
        iGpsPage.putExtra(KEY.COUNTRY_ID, idCountry);
        iGpsPage.putExtra(KEY.GROUP_CODE, gpsData.getGroupCode());
        iGpsPage.putExtra(KEY.GROUP_NAME, gpsData.getGroupName());
        iGpsPage.putExtra(KEY.SUB_GROUP_CODE, gpsData.getSubGroupCode());
        iGpsPage.putExtra(KEY.SUB_GROUP_NAME, gpsData.getSubGroupName());
        iGpsPage.putExtra(KEY.LOCATION_CODE, gpsData.getLocationCode());
        iGpsPage.putExtra(KEY.LOCATION_NAME, gpsData.getLocationName());
        iGpsPage.putExtra(KEY.LONGITUDE, tvLong.getText().toString());
        iGpsPage.putExtra(KEY.LONGITUDE, tvLong.getText().toString());


        dialog.showCustomDialog(MapActivity.this, "Location :", msg, iGpsPage);

    }

    /**
     * @description
     */
    /**
     * <p> This method Set and Show the Map position</p>
     *
     * @param title Tile of the Location
     * @param point Geo point Object
     */
    private void setMapPosition(String title, GeoPoint point) {


        anotherOverlayItemArray = new ArrayList<OverlayItem>();

        anotherOverlayItemArray.add(new OverlayItem(title, "", point));

        ItemizedIconOverlay<OverlayItem> anotherItemizedIconOverlay = new ItemizedIconOverlay<OverlayItem>(
                this, anotherOverlayItemArray, myOnItemGestureListener);
        /**
         * set marker in map
         */
        Marker startMarker = new Marker(mMapView);
        startMarker.setPosition(point);
        mMapView.getOverlays().add(anotherItemizedIconOverlay);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mMapView.getOverlays().add(startMarker);

        startMarker.setTitle(title);

       /* ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(mMapView);
        mMapView.getOverlays().add(myScaleBarOverlay);*/
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Projection projection = mMapView.getProjection();
        GeoPoint p, location;
        p = (GeoPoint) projection.fromPixels((int) event.getX(), (int) event.getY());

        projection = mMapView.getProjection();
        location = (GeoPoint) projection.fromPixels((int) event.getX(), (int) event.getY());
        String longitudeR = Double.toString(((double) location.getLongitudeE6()) / 1000000);
        String latitudeR = Double.toString(((double) location.getLatitudeE6()) / 1000000);
        /**
         * below code show a toast when user touch the screen
         */


        latitude = Double.parseDouble(latitudeR);


        longitude = Double.parseDouble(longitudeR);


        return true;
    }

    /**
     * @param title Title
     * @param lat   latitude
     * @param lng   Longitude
     */
    private void setMarker(String title, double lat, double lng) {


        GeoPoint point = new GeoPoint(lat, lng);
        setMapPosition(title, point);
    }

    /**
     * to off the back press button
     */

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

    }


    private void setLocationCoordinates(final String locationName, final String cCode, final String groupCode, final String subGroupCode, final String locationCode) {

        GPSLocationLatLong data = sqlH.getLocationSpecificLatLong(cCode, groupCode, subGroupCode, locationCode);
/**
 * if data exits in data base
 */

        if (data.getLatitude()!=null&&data.getLongitude()!=null&& data.getLatitude().length() > 1 && data.getLongitude().length() > 1) {
            if (!data.getLatitude().equals("null") && !data.getLongitude().equals("null"))
                setMarker(locationName, Double.valueOf(data.getLatitude()), Double.valueOf(data.getLongitude()));

            tv_exitLat.setText(data.getLatitude());
            tv_exitLong.setText(data.getLongitude());
            /**
             * center of the location
             * */
            if (!data.getLatitude().equals("null") && !data.getLongitude().equals("null")) {
                GeoPoint center = new GeoPoint(Double.valueOf(data.getLatitude()), Double.valueOf(data.getLongitude()));

                mapViewController.setCenter(center);
                mMapView.invalidate();
            }


        } else {

            tv_exitLat.setText("");
            tv_exitLong.setText("");

        }


        //  Log.d(TAG + 1, " Location name : " + locationName + " lat: " + data.getLatitude() + " long :" + data.getLongitude());


    }


    /**
     * This method Load the Location onto the spinner
     *
     * @param cCode Country Code
     */

    private void loadLocation(final String cCode) {
        SpinnerLoader.loadLocationLoader(mContext, sqlH, spLocation,  idLocation, strLocation, SQLiteQuery.loadLocationLoader_sql(cCode));


        spLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strLocation = ((SpinnerHelper) spLocation.getSelectedItem()).getValue();
                String idSpinner = ((SpinnerHelper) spLocation.getSelectedItem()).getId();

//                Log.d(TAG, " strLocation :" + strLocation + " idSpinner : " + idSpinner);
                if (!idSpinner.equals("00")) {
                    gpsData = new GPS_LocationDataModel();
                    gpsData.setAdmCountryCode(cCode);
                    gpsData.setLocationName(strLocation);
                    gpsData.setGroupCode(idSpinner.substring(0, 3));
                    gpsData.setSubGroupCode(idSpinner.substring(3, 6));
                    gpsData.setLocationCode(idSpinner.substring(6));
                    idLocation = idSpinner.substring(6);

                    String groupName = sqlH.getGroupNameFromDb(gpsData.getGroupCode());
                    String subGroupName = sqlH.getSubGroupNameFromDb(gpsData.getGroupCode(), gpsData.getSubGroupCode());

                    gpsData.setGroupName(groupName);
                    gpsData.setSubGroupName(subGroupName);
                    locationSelected = true;
                    tvGroupName.setText(groupName);
                    tvSubGroupName.setText(subGroupName);
                    setLocationCoordinates(strLocation, cCode, gpsData.getGroupCode(), gpsData.getSubGroupCode(), idLocation);
                } else {
                    locationSelected = false;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


}



