package com.ordinefacile.root.ordinefacile.service;


import android.content.Intent;
import android.icu.text.SimpleDateFormat;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;
import com.ordinefacile.root.ordinefacile.ui.code_scan.CodeOrScanActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by user on 6/4/2018.
 */

public class MyJobService extends JobService {
    private static final String TAG = "MyJobService";

    SaveData saveData;
    StartStopJobs startStopJobs;

    Date endDate ;
    long difference;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        saveData = new SaveData(getApplicationContext());
        startStopJobs = new StartStopJobs(getApplicationContext());

        String firstTime =  saveData.getTime();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date startDate = null;
        try {

            startDate = simpleDateFormat.parse(firstTime);

            String dgjdj = startDate.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }




        try {

            String currentDateTimeString2 = DateFormat.getDateTimeInstance().format(new Date());

            String[] separated = currentDateTimeString2.split(" ");

            String s4 = separated[3];

            String[] separated2 = s4.split(":");
            String s11 = separated2[0];
            String s22 = separated2[1];
            String total = s11+":"+s22;


            endDate = simpleDateFormat.parse( total);
            String djdj = endDate.toString();

             difference = endDate.getTime() - startDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }



        if(difference<0)
        {
            Date dateMax = null;
            try {
                dateMax = simpleDateFormat.parse("24:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date dateMin = null;
            try {
                dateMin = simpleDateFormat.parse("00:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            difference=(dateMax.getTime() -startDate.getTime() )+(endDate.getTime()-dateMin.getTime());
        }
        int days = (int) (difference / (1000*60*60*24));
        int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
        int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
        Log.i("log_tag","Hours: "+hours+", Mins: "+min);

        if (min>20) {


            Log.i("ALARM", "Hours: ");


            saveData.ClearAll();
            startStopJobs.cancelJob(StartStopJobs.JOB_TAG);

            Intent infoIntent = new Intent(getApplicationContext(), CodeOrScanActivity.class); //
            infoIntent.putExtra("service", "schedulim_stop");
            infoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(infoIntent);


        }


        Log.d(TAG, currentDateTimeString);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "Job cancelled!");
        return false;
    }

}

