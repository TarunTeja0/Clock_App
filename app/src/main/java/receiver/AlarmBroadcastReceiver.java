package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.clockapp.DisplayAlarmActivity;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentAlarm = new Intent(context, DisplayAlarmActivity.class);
        context.startActivity(intentAlarm);

    }
}
