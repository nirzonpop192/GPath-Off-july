package com.siddiquinoor.restclient.controller;

/**
 * This class is to handle session data
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 *
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {
	// LogCat tag
	private static String TAG = SessionManager.class.getSimpleName();

	// Shared Preferences
	SharedPreferences pref;

	Editor editor;
	Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Shared preferences file name
	private static final String PREF_NAME = "PCIAppLogin";
	
	private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

	private static final String USER_NAME = "UserName";
	private static final String STAFF_ID = "StaffID";
	private static final String USER_ID = "UserID";
	private static final String PASSWORD = "Password";
	private static final String C_CODE = "C_Code";

	public SessionManager(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	public void setLogin(boolean isLoggedIn) {

		editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

		// commit changes
		editor.commit();

		Log.d(TAG, "User login session modified!");
	}
	
	public boolean isLoggedIn(){
		return pref.getBoolean(KEY_IS_LOGGEDIN, false);
	}

    public void setUserName(String user_name){
        editor.putString(USER_NAME,user_name);
        editor.commit();
    }

    public String getUserName(){
        return pref.getString( USER_NAME, "User" );
    }

    public void setStaffID(String staffID){
        editor.putString(STAFF_ID,staffID);
        editor.commit();
    }

    public String getStaffId(){
        return pref.getString( STAFF_ID, "StaffID" );
    }

     public void setUserID(String userID){
        editor.putString(USER_ID,userID);
        editor.commit();
    }

    public String getUserID(){
        return pref.getString( USER_ID, "UserID" );
    }

     public void setUserPassword(String pass){
        editor.putString(PASSWORD,pass);
        editor.commit();
    }

    public String getUserPassword(){
        return pref.getString( PASSWORD, "Password" );
    }






    public void setUserCountryCode(String C_Code){
        editor.putString(C_CODE, C_Code);
        editor.commit();
    }

    public String getCountryCode(){
        return pref.getString( C_CODE, "C_Code" );
    }
}
