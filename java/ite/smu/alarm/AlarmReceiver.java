package ite.smu.alarm;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import static android.support.v4.content.WakefulBroadcastReceiver.startWakefulService;


public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            intent = new Intent(context, AlarmMain.class);

            PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            pi.send();

            boolean sound = MainActivity.nsound;
            if(sound)
                context.startService(new Intent(context, AlarmSoundService.class));

            boolean light = MainActivity.nlight;
            if(light)
                context.startService(new Intent(context, AlarmLight.class));

            boolean vib = MainActivity.nvib;
            if(vib)
              context.startService(new Intent(context, AlarmVibe.class));

            //This will send a notification message and show notification in notification tray
            ComponentName comp = new ComponentName(context.getPackageName(), AlarmNotificationService.class.getName());
            startWakefulService(context, (intent.setComponent(comp)));

        } catch (CanceledException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
