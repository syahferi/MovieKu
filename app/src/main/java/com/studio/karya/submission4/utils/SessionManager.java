package com.studio.karya.submission4.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREFS_NAME = "pref_movieKu";

    private static final String DAILY_REMINDER = "daily_reminder";
    private static final String RELEASE_REMINDER = "release_reminder";

    private final SharedPreferences sharedPreference;

    public SessionManager(Context context) {
        sharedPreference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setDailyReminder(boolean value) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putBoolean(DAILY_REMINDER, value);
        editor.apply();
    }

    public boolean isDailyReminder() {
        return sharedPreference.getBoolean(DAILY_REMINDER, false);
    }

    public void setReleaseReminder(boolean value) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putBoolean(RELEASE_REMINDER, value);
        editor.apply();
    }

    public boolean isReleaseReminder() {
        return sharedPreference.getBoolean(RELEASE_REMINDER, false);
    }
}
