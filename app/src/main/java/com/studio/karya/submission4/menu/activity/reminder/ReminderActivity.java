package com.studio.karya.submission4.menu.activity.reminder;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.studio.karya.submission4.R;

import static com.studio.karya.submission4.menu.activity.reminder.ReminderReceiver.DAILY_REMINDER;

public class ReminderActivity extends AppCompatActivity {

    private ReminderReceiver reminderReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        Switch switchDaily = findViewById(R.id.switch_daily_reminder);
        Switch switchRelease = findViewById(R.id.switch_release_reminder);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Reminder");
        }

        reminderReceiver = new ReminderReceiver();

        switchDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(ReminderActivity.this, "ON", Toast.LENGTH_SHORT).show();
                    reminderReceiver.setDailyReminder(getApplicationContext(), DAILY_REMINDER, "18:00", "Title MovieKu", "MovieKu is missing you :(");
                } else {
                    //cancel reminder
                }
            }
        });

    }
}
