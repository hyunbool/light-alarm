package ite.smu.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sm-pc on 2017-12-07.
 */

public class AlarmVibe extends Service{

    Vibrator mVibrator;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mVibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        mVibrator.vibrate(new long[]{1000,300},0);
   }

   public void onDestroy(){
       super.onDestroy();

       mVibrator.cancel();
   }
}
