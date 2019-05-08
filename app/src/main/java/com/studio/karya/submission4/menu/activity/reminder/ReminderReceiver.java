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
import com.studio.karya.submission4.api.repository.CallbackRepository;
import com.studio.karya.submission4.api.repository.Repository;
import com.studio.karya.submission4.menu.activity.MainActivity;
import com.studio.karya.submission4.model.Content;
import com.studio.karya.submission4.model.ContentResponse;
import com.studio.karya.submission4.utils.ConvertDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    private ArrayList<Content> contents = new ArrayList<>();
    private ConvertDate convertDate = new ConvertDate();

    @Override
    public void onReceive(Context context, Intent intent) {

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        String formatDate = sdf.format(date);

        String type = intent.getStringExtra(TYPE_REMINDER);

        if (type.equals(DAILY_REMINDER)) {
            String title = intent.getStringExtra(EXTRA_TITLE);
            String message = intent.getStringExtra(EXTRA_MESSAGE);
            showAlarmNotification(context, title, message, ID_DAILY_REMINDER);
        } else {
            getContentMovie(context, formatDate, convertDate); //formatDate
        }
    }

    public void setReminder(Context context, String type, String time, String title, String message) {

        if (isTimeInvalid(time, TIME_FORMAT)) return;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderReceiver.class);
        intent.putExtra(TYPE_REMINDER, type);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_MESSAGE, message);

        String[] timeArray = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        /*
        cek apakah waktu yang diset sebelum waktu sekarang
        jika ya, tambah 1 hari. Agar alarm bunyi keesokannya
        */
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }

        int requestCode = type.equals(DAILY_REMINDER) ? ID_DAILY_REMINDER : ID_RELEASE_REMINDER;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    public void cancelAlarm(Context context, String type) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderReceiver.class);
        int requestCode = type.equalsIgnoreCase(DAILY_REMINDER) ? ID_DAILY_REMINDER : ID_RELEASE_REMINDER;

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        pendingIntent.cancel();

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
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

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_time)
                .setContentTitle(title)
                .setContentText(msg)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound)
                .setAutoCancel(true);

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

    private void getContentMovie(final Context context, final String date, final ConvertDate convertDate) {
        Repository repository = new Repository();
        repository.getDataMovie(new CallbackRepository<ContentResponse>() {
            @Override
            public void onDataLoaded(ContentResponse data) {
                contents = data.getContentList();
                for (int i = 0; i < contents.size(); i++) {
                    String dateMovie = convertDate.date(contents.get(i).getReleaseDate());
                    if (dateMovie.equals(date)) {
                        String title = contents.get(i).getTitleFilm();
                        String msg = title + " has been release today!";
                        showAlarmNotification(context, title, msg, ID_RELEASE_REMINDER);
                    }
                }
            }

            @Override
            public void onDataError(String message) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
