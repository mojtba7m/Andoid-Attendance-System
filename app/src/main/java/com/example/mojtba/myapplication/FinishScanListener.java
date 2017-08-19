package com.example.mojtba.myapplication;

import java.util.ArrayList;

/**
 * Created by mojtba on 02/07/17.
 */

public interface FinishScanListener {


    /**
     * Interface called when the scan method finishes. Network operations should not execute on UI thread
     * @param  ArrayList of {@link ClientScanResult}
     */

    public void onFinishScan(ArrayList<ClientScanResult> clients);

}