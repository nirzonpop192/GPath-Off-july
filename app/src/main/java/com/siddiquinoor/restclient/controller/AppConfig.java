package com.siddiquinoor.restclient.controller;

/**
 * This class is to define basic Application configuration
 * the link of the web server  *
 *
 * @author Faisal Mohammad
 * @version 7.0.0
 * @since 1.0
 */

public class AppConfig {


    /***********************************************************************
     * UAT
     * //Windows Server Azure VM Live Server
     */
  //public static final String API_LINK = "http://pciapp.cloudapp.net/datacraft/api/";
  //public static final String API_LINK_ENU = "http://pciapp.cloudapp.net/datacraft/api/index.php?enu";
    //LIVE LINK
    //  public static final String API_LINK = "http://pciapp.cloudapp.net/apilive/";
    /************************************************************************/
    //LIVE LINK
    public static final String API_LINK = "http://pciapp.cloudapp.net/datacraft/apilive/";
    public static final String API_LINK_ENU = "http://pciapp.cloudapp.net/datacraft/apilive/index.php?enu";


    /***********************************************************************
     * //  Localhost
     */
    //  public static final String API_LINK = "http://192.168.49.1/api/local/";
    /************************************************************************/
    /***********************************************************************
     * //  Localhost out side ngrok
     */
    //  public static final String API_LINK = "http://83cb7db6.ngrok.io/api/";
    /************************************************************************/
    // Application developments  Environment
    public static Boolean DEV_ENVIRONMENT = false; // false / true


}
