package com.siddiquinoor.restclient.manager;

/**
 * Created by Faisal on 1/12/2016.
 *
 */
public class dataUploadDB implements Comparable<dataUploadDB> {

    String _id = null;
    String _syntax=null;
    int _sqn=-1;
    @Override
    public int compareTo(dataUploadDB another) {
        return 0;
    }
}
