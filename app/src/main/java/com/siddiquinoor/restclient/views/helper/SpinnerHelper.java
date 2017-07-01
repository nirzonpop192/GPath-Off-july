package com.siddiquinoor.restclient.views.helper;

/**
 * This class is responsible produce Spinner
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 *
 */
public class SpinnerHelper {

    private int position;
    private String databaseId;
    private String databaseValue;

    public SpinnerHelper ( int position, String databaseId , String databaseValue ) {
        this.position = position;
        this.databaseId = databaseId;
        this.databaseValue = databaseValue;
    }

    public int getSpinnerPosition () {
        return position;
    }

    public String getId () {
        return databaseId;
    }

    public String getValue () {
        return databaseValue;
    }

    @Override
    public String toString () {
        return databaseValue;
    }

}