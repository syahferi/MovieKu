package com.studio.karya.submission4.menu.activity.reminder;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.studio.karya.submission4.R;
import com.studio.karya.submission4.utils.SessionManager;

import static com.studio.karya.submission4.menu.activity.reminder.ReminderReceiver.DAILY_REMINDER;
import static com.studio.karya.submission4.menu.activity.reminder.ReminderReceiver.RELEASE_REMINDER;

public class ReminderActivity extends AppCompatActivity {

    private static final String DAILY_REMINDER_TIME = "07:00";
    private static final String RELEASE_REMINDER_TIME = "08:00";

    private ReminderReceiver reminderReceiver;
    private SessionManager sessionManager;

    private Switch switchDaily, switchRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        switchDaily = findViewById(R.id.switch_daily_reminder);
        switchRelease = findViewById(R.id.switch_release_reminder);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.toolbar_reminder));
        }

        sessionManager = new SessionManager(getApplicationContext());
        reminderReceiver = new ReminderReceiver();

        checkState();

        switchDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sessionManager.setDailyReminder(true);
                    reminderReceiver.setReminder(getApplicationContext(), DAILY_REMINDER, DAILY_REMINDER_TIME, getResources().getString(R.string.title_notif), getResources().getString(R.string.sub_msg_notif));
                    Toast.makeText(ReminderActivity.this, getResources().getString(R.string.toast_alarm_active), Toast.LENGTH_SHORT).show();
                } else {
                    //cancel reminder
                    sessionManager.setDailyReminder(false);
                    reminderReceiver.cancelAlarm(getApplicationContext(), DAILY_REMINDER);
                    Toast.makeText(ReminderActivity.this, getResources().getString(R.string.toast_alarm), Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchRelease.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sessionManager.setReleaseReminder(true);
                    reminderReceiver.setReminder(getApplicationContext(), RELEASE_REMINDER, RELEASE_REMINDER_TIME, "", "");
                    Toast.makeText(ReminderActivity.this, getResources().getString(R.string.toast_alarm_active), Toast.LENGTH_SHORT).show();
                } else {
                    //cancel reminder
                    sessionManager.setReleaseReminder(false);
                    reminderReceiver.cancelAlarm(getApplicationContext(), RELEASE_REMINDER);
                    Toast.makeText(ReminderActivity.this, getResources().getString(R.string.toast_alarm), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkState() {

        //daily reminder
        if (sessionManager.isDailyReminder()) {
            switchDaily.setChecked(true);
        } else {
            switchDaily.setChecked(false);
        }

        //release reminder
        if (sessionManager.isReleaseReminder()) {
            switchRelease.setChecked(true);
        } else {
            switchRelease.setChecked(false);
        }
    }
}
