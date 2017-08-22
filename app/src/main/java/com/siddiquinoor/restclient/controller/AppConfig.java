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
//    public static final String API_LINK = "http://pciapp.cloudapp.net/datacraft/api/";
//    public static final String API_LINK_ENU = "http://pciapp.cloudapp.net/datacraft/api/index.php?enu";
//    public static final String API_LINK_VER = "http://pciapp.cloudapp.net/datacraft/api/index.php?ver";
    //LIVE LINK
    //  public static final String API_LINK = "http://pciapp.cloudapp.net/apilive/";
    /************************************************************************/
    //LIVE LINK
     public static final String API_LINK = "http://pciapp.cloudapp.net/datacraft/apilive/";
     public static final String API_LINK_ENU = "http://pciapp.cloudapp.net/datacraft/apilive/index.php?enu";
     public static final String API_LINK_VER = "http://pciapp.cloudapp.net/datacraft/apilive/index.php?ver";

    /*********************************************************************
     * apk download link from Azure
     ********************************************************************/
    public static final String NEW_APK_DOWNLOAD_LINK = "https://portalvhdsc6m1n4h3n71lj.blob.core.windows.net/gpath-apk/G-path_OFF_20.apk";
    /********************************************************************
     *
     **********************************************************************/

    /***********************************************************************
     *   Localhost
     ***********************************************************************/
    //  public static final String API_LINK = "http://192.168.49.1/api/local/";
    /**********************************************************************
     *    end
     ***********************************************************************/

    /***********************************************************************
     *   Localhost out side ngrok
     ***********************************************************************/
    //  public static final String API_LINK = "http://83cb7db6.ngrok.io/api/";
    /***********************************************************************
     * end of Localhost out side ngrok
     ***********************************************************************/

    // Application developments  Environment
    public static Boolean DEV_ENVIRONMENT = false; // false / true

    /**
     * new version apk name apk
     */
    public static final String DOWNLOADED_APK_NAME = "Gpath.apk";

}
