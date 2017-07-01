package com.siddiquinoor.restclient.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.siddiquinoor.restclient.views.notifications.CustomToast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by pop on 4/19/2017.
 */

public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();
    public static final String EXPORT_GOF_FILE = "EXPORT.gof";

    /**
     * Creates the specified <code>toFile</code> as a byte for byte copy of the
     * <code>fromFile</code>. If <code>toFile</code> already exists, then it
     * will be replaced with a copy of <code>fromFile</code>. The name and path
     * of <code>toFile</code> will be that of <code>toFile</code>.<br/>
     * <br/>
     * <i> Note: <code>fromFile</code> and <code>toFile</code> will be closed by
     * this function.</i>
     *
     * @param fromFile - FileInputStream for the file to copy from.
     * @param toFile   - FileInputStream for the file to copy to.
     */

    public static void copyFile(FileInputStream fromFile, FileOutputStream toFile) throws IOException {
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            fromChannel = fromFile.getChannel();
            toChannel = toFile.getChannel();
            fromChannel.transferTo(0, fromChannel.size(), toChannel);
        } finally {
            try {
                if (fromChannel != null) {
                    fromChannel.close();
                }
            } finally {
                if (toChannel != null) {
                    toChannel.close();
                }
            }
        }
    }

    public static void dataBaseCopyFromPackageToInternalRoot(Context context, final String sourcePath, final String destinationPath, String msg) {
        try {

            String root = Environment.getExternalStorageDirectory().toString();

//            File sd = Environment.getExternalStorageDirectory();                                   // get the internal root directories
            File sd = new File(root + "/GpathOffline/");                                             // get the internal root directories root/GpathOffline path

            if (!sd.exists())
                sd.mkdirs();

            if (sd.canWrite()) {
                File currentDB = new File(sourcePath);
                File backupDB = new File(sd, destinationPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();

                    CustomToast.show(context, msg);

                }
            }
        } catch (Exception e) {
            Toast.makeText(context, "Export Failed", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Exception : " + e.getMessage());

        }
    }

    public static void  deleteExportDatabase(){
        String root = Environment.getExternalStorageDirectory().toString();
        File exportDbFile = new File(root + "/GpathOffline/" + EXPORT_GOF_FILE);                                             // get the internal root directories root/GpathOffline path


        if (exportDbFile.exists()) {                                                                // if file existed
            if (exportDbFile.delete())                                                              // if file delete successful
                Log.i("File", "Export Database deleted");
        }
    }
}
