package com.ordinefacile.root.ordinefacile.service;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by user on 6/4/2018.
 */

public class StartStopJobs {

    Context context;
    FirebaseJobDispatcher mDispatcher;
    SaveData saveData;
    private static final String JOB_TAG = "MyJobService";
    private static final String TAG = "MyJobServiceCk";


    public StartStopJobs(Context context) {
        this.context = context;
        mDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        saveData = new SaveData(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String scheduleJob() {

        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);



        String hours = Integer.toString(hourOfDay);
        String minutes = Integer.toString(minute);

        saveData.saveHours(hours);
        saveData.saveMinits(minutes);

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        String[] separated = currentDateTimeString.split(" ");
        String ZERO = separated[0];
        String ZERO1 = separated[1];
        String ZERO2 = separated[2];
        String ZERO2gg = separated[3];

        String[] separated2 = ZERO2gg.split(":");
        String ZgERO = separated2[0];
        String ZfERO1 = separated2[1];
        String total = ZgERO+":"+ZfERO1;

        Log.d(TAG, currentDateTimeString);

        Job myJob = mDispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .setTag(JOB_TAG)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(60, 60+1))
                .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                .setReplaceCurrent(false)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .build();
        mDispatcher.mustSchedule(myJob);
        Toast.makeText(context, "jobs  start", Toast.LENGTH_LONG).show();

        return currentDateTimeString;
    }

    private void cancelJob(String jobTag) {
        if ("".equals(jobTag)) {
            mDispatcher.cancelAll();
        } else {
            mDispatcher.cancel(jobTag);
        }
        Toast.makeText(context, "jobs  stop", Toast.LENGTH_LONG).show();
    }
}
