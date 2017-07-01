package com.siddiquinoor.restclient.controller;

/**
 * This class is used as an Application Controller
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 *
 */

import android.app.Application;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.content.Context;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.siddiquinoor.restclient.manager.LruBitmapCache;

public class AppController extends Application {

	public static final String TAG = AppController.class.getSimpleName();

	private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

	private static AppController mInstance;


   // private boolean status = false;
    private int current_record = 0;
    private int record_total = 0;
    private Context main_view;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

//    public boolean getAsyncStatus() {
//        return this.status;
//    }
//
//    public void setAsyncStatus(boolean new_status) {
//        this.status = new_status;
//    }

    public void setTotalNumber(int record_total) {
        this.record_total = record_total;
    }

    public int getTotalNumber() {
        return this.record_total;	}

    public void setDefaultNumber(){
        this.current_record = 0;
    }

    public void updateRecord() {
        this.current_record++;
    }

    public int getCurrentRecord() {
        return this.current_record;
    }

    public void setMainViewContext(View v){
        this.main_view = v.getContext();
    }



	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
	}

	public static synchronized AppController getInstance() {
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}

    // Online Image Loader
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue, new LruBitmapCache());
        }
        return this.mImageLoader;
    }



    public <T> void addToRequestQueue(Request<T> req, String tag) {
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        int socketTimeout = 3600000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);

		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
}