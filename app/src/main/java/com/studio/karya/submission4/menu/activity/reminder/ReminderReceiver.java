package com.studio.karya.submission4.menu.activity.reminder;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.studio.karya.submission4.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ReminderReceiver extends BroadcastReceiver {

    public static final String DAILY_REMINDER = "daily_reminder";
    public static final String RELEASE_REMINDER = "release_reminder";
    public static final String TYPE_REMINDER = "type_reminder";
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_TITLE = "title";

    private final int ID_DAILY_REMINDER = 101;
    private final int ID_RELEASE_REMINDER = 201;

    String TIME_FORMAT = "HH:mm";

    @Override
    public void onReceive(Context context, Intent intent) {

        String type = intent.getStringExtra(TYPE_REMINDER);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String message = intent.getStringExtra(EXTRA_MESSAGE);

        int notifId = type.equalsIgnoreCase(DAILY_REMINDER) ? ID_DAILY_REMINDER : ID_RELEASE_REMINDER;

        showAlarmNotification(context, title, message, notifId);
    }

    public void setDailyReminder(Context context, String type, String time, String title, String message) {
        if (isTimeInvalid(time, TIME_FORMAT)) return;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderReceiver.class);
        intent.putExtra(TYPE_REMINDER, type);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_MESSAGE, message);

        String[] timeArray = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_DAILY_REMINDER, intent, 0);
        if (alarmManager != null) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }

    public boolean isTimeInvalid(String time, String format) {
        try {
            DateFormat df = new SimpleDateFormat(format, Locale.getDefault());
            df.setLenient(false);
            df.parse(time);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    private void showAlarmNotification(Context context, String title, String msg, int notifId) {

        String CHANNEL_ID = "001";
        String CHANNEL_NAME = "Notification MovieKu";

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_time)
                .setContentTitle(title)
                .setContentText(msg)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});

            builder.setChannelId(CHANNEL_ID);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();
        if (notificationManager != null) {
            notificationManager.notify(notifId, notification);
        }
    }
}
