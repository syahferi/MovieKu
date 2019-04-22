package com.studio.karya.submission4.db.DDL;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static String TABLE_MOVIE = "tableMovie";
    public static String TABLE_TV = "tableTv";

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
    }

}
