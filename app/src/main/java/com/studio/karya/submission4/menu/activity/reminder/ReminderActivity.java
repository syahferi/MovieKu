package com.studio.karya.submission4.menu.activity.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Switch;

import com.studio.karya.submission4.R;

public class ReminderActivity extends AppCompatActivity {

    private Switch switchDaily, switchRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        switchDaily = findViewById(R.id.switch_daily_reminder);
        switchRelease = findViewById(R.id.switch_release_reminder);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Reminder");
        }

    }
}
