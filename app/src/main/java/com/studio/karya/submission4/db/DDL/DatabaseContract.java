package com.studio.karya.submission4.db.DDL;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static String TABLE_MOVIE = "tableMovie";
    public static String TABLE_TV = "tableTv";

    public static final String AUTHORITY = "com.studio.karya.submission4";
    private static final String SCHEME = "content";

    public static final class TableColumns implements BaseColumns {
        public static String ID_CONTENT = "id_content";
        public static String TITLE = "title";
        public static String OVERVIEW = "overview";
        public static String RELEASE_DATE = "release_date";
        public static String POSTER = "poster";
        public static String BACKDROP_POSTER = "backdrop_poster";
        public static String POPULARITY = "popularity";
        public static String VOTE_COUNT = "vote_count";
        public static String VOTE_AVG = "vote_avg";

        public static final Uri CONTENT_URI_MOVIE = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_MOVIE)
                .build();

        public static final Uri CONTENT_URI_TV = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_TV)
                .build();
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    /*public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }*/
}
