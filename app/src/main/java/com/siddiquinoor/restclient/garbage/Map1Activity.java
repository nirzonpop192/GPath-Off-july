package com.siddiquinoor.restclient.garbage;

/**
 * Created by USER on 6/20/2016.
 * hello
 */
public class Map1Activity {
    /*
public class MapActivity extends FragmentActivity implements OnMapReadyCallback,
        LocationListener  ,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG=MapActivity.class.getSimpleName();


    private  static final long INTERVAL=1000*60*10;// 10 minute
    private static final long FASTEST_INTERVAL=1000*60*5; // 5 MINUTE
    private Location mCurrentLocation;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    String mLastUpdateTime;
    GPSTracker gpsTracker;
    private Button btn_home;
    private TextView tvLat;
    private TextView tvLong;
    private SQLiteHandler sqlH;

    private LatLng currentLocation;

    public LatLng getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(LatLng currentLocation) {
        this.currentLocation = currentLocation;
    }

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    //     load spinner
   private Spinner spGroup;
   private Spinner spSubGroup;
   private Spinner splocation;

    private  String idCountry;
    private String idGroup;
    private String idSubGroup;
    private String idLocation;

    private long locationId;// for update the location lat long
    int position = 0;
    private String strGroup;
    private String strSubGroup;
    Button btn_save_location;

    android.support.v4.app.FragmentManager fManager = getSupportFragmentManager();

    // show ing dialog if gps is not connected
    private ADNotificationManager gpsDialog;
    Context mContext;

    private String strLocation;

    protected void createLocationRequest(){
        mLocationRequest=new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get Country Code
        idCountry=getIntent().getExtras().getString("ID_COUNTRY");

        // before set content view set the service request

        gpsTracker=new GPSTracker(this);// take the current location from gprTracker class

        createLocationRequest();
        mGoogleApiClient=new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        if (!isGooglePlayServiceAvailable()) {// seafty for activity
            finish();// if service is not active close this activity
        }


        setContentView(R.layout.activity_map);

        //final SupportMapFragment myMAPF = (SupportMapFragment) fManager.findFragmentById(R.id.onlineMap);
       // mMap = myMAPF.getMap();

       // mMap=((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.onlineMap)).getMap();
        viewReference();

        mContext =MapActivity.this;
        sqlH=new SQLiteHandler(this);

       // regi.setVisibility(View.GONE);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent goHome = new Intent(mContext, MainActivity.class);
                startActivity(goHome);
            }
        });

        btn_save_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isGPSEnable()) {
                    if (idLocation != null) {
                        gpsDialog = new ADNotificationManager();
                        GPSLocationLatLong currentLocation = new GPSLocationLatLong(idCountry,idGroup,idSubGroup,idLocation,
                                String.valueOf(gpsTracker.getLatitude()), String.valueOf(gpsTracker.getLongitude()));
                        sqlH.updateGpsLocation(currentLocation);
                        SQLServerSyntaxGenerator geoLocationTable=new SQLServerSyntaxGenerator();
                        geoLocationTable.setAdmCountryCode(idCountry);
                        geoLocationTable.setGrpCode(idGroup);
                        geoLocationTable.setSubGrpCode(idSubGroup);
                        geoLocationTable.setLocationCode(idLocation);
                        geoLocationTable.setLong(currentLocation.getLongitude());
                        geoLocationTable.setLatd(currentLocation.getLatitude());
                        geoLocationTable.setEntryBy(sqlH.getUserId());
                        String time="";
                        try {
                            time=  getDateTime();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        geoLocationTable.setEntryDate(time);

                        sqlH.insertIntoUploadTable(geoLocationTable.updateGPS_GeoLocationTable());

                        gpsDialog.showConfirmDialog(mContext, "Location has been Saved");
                        Log.d(TAG,"in SAVE map: GPS TRacker Lat"+gpsTracker.getLatitude()+"\n long :"+gpsTracker.getLongitude());
                    }


                }
            }
        });

        int checkGooglePlayServices =    GooglePlayServicesUtil.isGooglePlayServicesAvailable(MapActivity.this);
        if (checkGooglePlayServices != ConnectionResult.SUCCESS) {
            // google play services is missing!!!!
    */
/* Returns status code indicating whether there was an error.
    Can be one of following in ConnectionResult: SUCCESS, SERVICE_MISSING, SERVICE_VERSION_UPDATE_REQUIRED, SERVICE_DISABLED, SERVICE_INVALID.
    *//*

//            GooglePlayServicesUtil.getErrorDialog(checkGooglePlayServices, MapActivity.this, 1122).show();
        }
        loadGroup();

        //setUpMapIfNeeded();

    }
    String getDateTime() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM-dd-yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date).toString();
    }

    private void viewReference() {
        tvLat = (TextView) findViewById(R.id.tv_latitude);
        tvLong = (TextView) findViewById(R.id.tv_longitude);
        spGroup= (Spinner) findViewById(R.id.spGroup);
        spSubGroup= (Spinner) findViewById(R.id.spGPS_SubGroup);
        btn_save_location= (Button) findViewById(R.id.btnRegisterFooter);
        btn_save_location.setText("Save");

       btn_home = (Button) findViewById(R.id.btnHomeFooter);
        // View regi=  findViewById(R.id.btnRegisterFooter);// for invasable the button

        splocation= (Spinner) findViewById(R.id.spGPS_location);
    }


    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    private boolean isGooglePlayServiceAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (status==ConnectionResult.SUCCESS)
            return true;
        else
            return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }
    // disconnect api service
    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();
    }



    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
         //   final SupportMapFragment MAPF = (SupportMapFragment) fManager.findFragmentById(R.id.onlineMap);
          //  mMap = MAPF.getMap();
            // map setting here
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.onlineMap))
                    .getMap();
//            mMap.getUiSettings().setZoomControlsEnabled(true);// set zoom controll button
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.getUiSettings().setCompassEnabled(true); // set compass
            mMap.getUiSettings().setZoomControlsEnabled(true);


            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    // it check the GPS Is on
    private  boolean isGPSEnable(){
        LocationManager locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
    */
/**
 * This is where we can add markers or lines, add listeners or move the camera. In this case, we
 * just add a marker near Africa.
 * <p/>
 * This should only be called once and when we are sure that {@link #mMap} is not null.
 * location change listener implement method
 * google API Connection Call back implements method
 * ConnectionFailedListener
 * <p/>
 * Converter : convert lat long to string
 * <p/>
 * LOAD :: Group in the group spinner
 * <p/>
 * LOAD :: SUb Group
 * <p/>
 * LOAD :: Location to the Location Spinner
 * location change listener implement method
 * google API Connection Call back implements method
 * ConnectionFailedListener
 * <p/>
 * Converter : convert lat long to string
 * <p/>
 * LOAD :: Group in the group spinner
 * <p/>
 * LOAD :: SUb Group
 * <p/>
 * LOAD :: Location to the Location Spinner
 * location change listener implement method
 * google API Connection Call back implements method
 * ConnectionFailedListener
 * <p/>
 * Converter : convert lat long to string
 * <p/>
 * LOAD :: Group in the group spinner
 * <p/>
 * LOAD :: SUb Group
 * <p/>
 * LOAD :: Location to the Location Spinner
 * location change listener implement method
 * google API Connection Call back implements method
 * ConnectionFailedListener
 * <p/>
 * Converter : convert lat long to string
 * <p/>
 * LOAD :: Group in the group spinner
 * <p/>
 * LOAD :: SUb Group
 * <p/>
 * LOAD :: Location to the Location Spinner
 *//*


    private void setUpMap() {



        if(isGPSEnable()){
            addCurrentLocationMarker();//
        }
    }
    */
/** location change listener implement method
 * *//*


    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        setUpMap();
    }

    private void addCurrentLocationMarker() {
        MarkerOptions options = new MarkerOptions();



        LatLng mcurrentLatLng = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());

        options.position(mcurrentLatLng);
        Marker currentMMarker = mMap.addMarker(options);

        currentMMarker.setTitle("Current Location");
        Log.d(TAG, "Marker added.............................");
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mcurrentLatLng, 13));
        Log.d(TAG, "Zoom done.............................");

        tvLat.setText(doubleToString(gpsTracker.getLatitude()));
        tvLong.setText(doubleToString(gpsTracker.getLongitude()));


    }
    // 2 marker method
    private void addLoadedLocationMarker(String lat,String log ,String locationName) {
        MarkerOptions options = new MarkerOptions();



        LatLng selectedLatLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(log));
        options.position(selectedLatLng);
        Marker loadMMarker = mMap.addMarker(options);
        if(isGPSEnable()){
            LatLng mcurrentLatLng = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
            double distanceDifferents= SphericalUtil.computeDistanceBetween(selectedLatLng,mcurrentLatLng);
            if (distanceDifferents<650000){
                MarkerOptions cOption=new MarkerOptions();
                cOption.position(mcurrentLatLng);
                Marker currentMMarker = mMap.addMarker(cOption);
                currentMMarker.setTitle("Current Location");
            }
        }

//
        loadMMarker.setTitle(locationName);
        Log.d(TAG, "Marker added.............................");
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedLatLng, 13));




        //  gpsDialog.showSettingsAlert(mContext);


        tvLat.setText(doubleToString(gpsTracker.getLatitude()));
        tvLong.setText(doubleToString(gpsTracker.getLongitude()));


    }



    */
/** google API Connection Call back implements method*//*

    @Override
    public void onConnected(Bundle bundle) {
        starOnLoactionUpdate();

    }

    private void starOnLoactionUpdate() {
        PendingResult<Status> pendingResult= LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
    */
/**
 * ConnectionFailedListener
 * *//*

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    */
/**
 * Converter : convert lat long to string
 *//*

    public String doubleToString( double value){
        String str=Double.toString(value);
        int op=str.indexOf(".");//get decimal index
        try{
            str=str.substring(0,op+5);// show only 4 decimal number
        }catch (StringIndexOutOfBoundsException e){
            e.printStackTrace();
            str="";
        }
        return str;
    }

    // if back button is press in mapActivity it go to main Activity

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
      */
/*  finish();
        Intent goMap=new Intent(mContext,MapActivity.class);
        startActivity(goMap);*//*

    }

    */
/**
 * LOAD :: Group in the group spinner
 *//*

    private void loadGroup(){
       // String criteria=" WHERE "+SQLiteHandler.ADM_COUNTRY_CODE_COL+" = '"+idCountry+"'";
        String criteria="";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listGroup = sqlH.getListAndID(SQLiteHandler.GPS_GROUP_TABLE, criteria, null,false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(mContext, R.layout.spinner_layout, listGroup);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout );
        // attaching data adapter to spinner
        spGroup.setAdapter(dataAdapter);


        if (idGroup != null) {
            for (int i = 1; i < spGroup.getCount(); i++) {
                String group = spGroup.getItemAtPosition(i).toString();
                if (group.equals(strGroup)) {
                    position = i;
                }
            }
            spGroup.setSelection( position );
        }



        spGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getValue();
                idGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getId();

                loadSubGroup(idGroup);
                //Log.d(TAG, "District selected: " + strDistrict);
                //Log.d(TAG, "ID is: " + idDist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    */
/**
 * LOAD :: SUb Group
 *//*

    private void loadSubGroup(final String idGroup){
       // final String idG=idGroup;
        //+SQLiteHandler.ADM_COUNTRY_CODE_COL+" = '"+idCountry+"' AND "
        String criteria = " WHERE " + SQLiteHandler.GROUP_CODE_COL + "='" + idGroup + "'" ;

        // Spinner Drop down elements for District
        List<SpinnerHelper> listsubGroup = sqlH.getListAndID(SQLiteHandler.GPS_SUB_GROUP_TABLE, criteria, null,false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(mContext, R.layout.spinner_layout, listsubGroup);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout );
        // attaching data adapter to spinner
        spSubGroup.setAdapter(dataAdapter);

        // Set the string to the position
        if (idSubGroup != null) {
            for (int i = 0; i < spSubGroup.getCount(); i++) {
                String subGroup = spSubGroup.getItemAtPosition(i).toString();
                if (subGroup.equals(strSubGroup)) {
                    position = i;
                }
            }
            spSubGroup.setSelection( position );
        }



        spSubGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGroup = ((SpinnerHelper) spSubGroup.getSelectedItem()).getValue();
                idSubGroup = ((SpinnerHelper) spSubGroup.getSelectedItem()).getId();

                loadLocation(idGroup, idSubGroup);
                //Log.d(TAG, "District selected: " + strDistrict);
                //Log.d(TAG, "ID is: " + idDist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    */
/**
 * LOAD :: Location to the Location Spinner
 *//*

    private void loadLocation(final String groupCode , final String subGroupCode){
      //  Log.d(TAG,"in load location");
        String criteria =  " WHERE "+SQLiteHandler.ADM_COUNTRY_CODE_COL +" = '"+idCountry+"' AND "+ sqlH.GROUP_CODE_COL + "='" + groupCode + "' AND " + sqlH.SUB_GROUP_CODE_COL + "='" + subGroupCode + "'";

        // Spinner Drop down elements for Location
        List<SpinnerHelper> listlocation = sqlH.getListAndID(SQLiteHandler.GPS_LOCATION_TABLE, criteria, null,false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(mContext, R.layout.spinner_layout, listlocation);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout );  // Set Drop down layout style

        splocation.setAdapter(dataAdapter);  // attaching data adapter to spinner


        if (idLocation != null) {
            for (int i = 0; i < splocation.getCount(); i++) {
                String location = splocation.getItemAtPosition(i).toString();
                if (location.equals(strLocation)) {
                    position = i;
                }
            }
            splocation.setSelection( position );
        }



        splocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strLocation = ((SpinnerHelper) splocation.getSelectedItem()).getValue();
                idLocation = ((SpinnerHelper) splocation.getSelectedItem()).getId();

                // HERE LOAD LAT LONG IN EDIT TEXT loadLocation(idG,idSubGroup);
                GPSLocationLatLong latLong = sqlH.getLocationSpecificLatLong(idCountry,groupCode, subGroupCode, idLocation);
                tvLat.setText(latLong.getLatitude());
                tvLong.setText(latLong.getLongitude());
               // setLocationId(latLong.getId());// for protection

                String strLat= tvLat.getText().toString();
                String strLong= tvLong.getText().toString();

                if (!strLat.equals("")) {
                    if (  !strLat.equals("null")) {
                        Log.d(TAG, " lat- " + tvLat.getText().toString() + " , long " + tvLong.getText().toString());
                        addLoadedLocationMarker(strLat, strLong, strLocation);
                        tvLat.setText(latLong.getLatitude());
                        tvLong.setText(latLong.getLongitude());
                    } else {
                        tvLat.setText("Haven't saved yet");
                        tvLong.setText("Haven't saved yet");
                    }
                }

                //Log.d(TAG, "District selected: " + strDistrict);
                //Log.d(TAG, "ID is: " + idDist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
*/

}
