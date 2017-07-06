package com.siddiquinoor.restclient.version;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.controller.AppConfig;

import java.io.File;

/**
 * Created by TD-Android on 7/6/2017.
 */
public class ApkDownloadCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        //get destination to update file and set Uri
        //TODO: First I wanted to store my update .apk file on internal storage for my app but apparently android does not allow you to open and install
        //aplication with existing package from there. So for me, alternative solution is Download directory in external storage. If there is better
        //solution, please inform us in comment
        String destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
        String fileName = AppConfig.DOWNLOADED_APK_NAME;
        final Uri uri_apk_view = Uri.parse("file://" + destination);
        destination += fileName;
        final Uri uri = Uri.parse("file://" + destination);

        //Delete update file if exists
        File file = new File(destination);
        if (file.exists())
            file.delete();

        //get url of app on server
        String url = AppConfig.NEW_APK_DOWNLOAD_LINK;

        //set downloadmanager
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription(context.getString(R.string.notification_description));
        request.setTitle(context.getString(R.string.app_name));

        //set destination
        request.setDestinationUri(uri);

        // get download service and enqueue file
        final DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        final long downloadId = manager.enqueue(request);
//set BroadcastReceiver to install app when .apk is downloaded
        BroadcastReceiver onComplete = new BroadcastReceiver() {
            public void onReceive(Context ctxt, Intent intent) {

                Intent install = new Intent(Intent.ACTION_GET_CONTENT);
                install.setDataAndType(Uri.fromFile(new File(uri_apk_view.toString())), manager.getMimeTypeForDownloadedFile(downloadId));
                install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                context.unregisterReceiver(this);
                Activity activity = (Activity) context;
                activity.finish();
                context.startActivity(install);


//

            }
        };
        //register receiver for when .apk download is compete

        context.getApplicationContext().registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }
    /*   String destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";

    final Uri uri_apk_view = Uri.parse("file://" + destination);
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        install.setDataAndType(uri_apk_view,
                manager.getMimeTypeForDownloadedFile(downloadId));

        context.unregisterReceiver(this);
        context.finish();
        context.startActivity(install);
    }*/
}
