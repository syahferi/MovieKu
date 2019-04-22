package com.studio.karya.submission4.db.DDL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbdicoding";
    static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_TABLE_MOVIE =
            String.format(
                    "CREATE TABLE %s" +
                            "(%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL)",
                    DatabaseContract.TABLE_MOVIE,
                    DatabaseContract.TableColumns._ID,
                    DatabaseContract.TableColumns.ID_CONTENT,
                    DatabaseContract.TableColumns.TITLE,
                    DatabaseContract.TableColumns.OVERVIEW,
                    DatabaseContract.TableColumns.RELEASE_DATE,
                    DatabaseContract.TableColumns.POSTER,
                    DatabaseContract.TableColumns.BACKDROP_POSTER,
                    DatabaseContract.TableColumns.POPULARITY,
                    DatabaseContract.TableColumns.VOTE_COUNT,
                    DatabaseContract.TableColumns.VOTE_AVG);

    private static final String SQL_CREATE_TABLE_TV =
            String.format(
                    "CREATE TABLE %s" +
                            "(%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL)",
                    DatabaseContract.TABLE_TV,
                    DatabaseContract.TableColumns._ID,
                    DatabaseContract.TableColumns.ID_CONTENT,
                    DatabaseContract.TableColumns.TITLE,
                    DatabaseContract.TableColumns.OVERVIEW,
                    DatabaseContract.TableColumns.RELEASE_DATE,
                    DatabaseContract.TableColumns.POSTER,
                    DatabaseContract.TableColumns.BACKDROP_POSTER,
                    DatabaseContract.TableColumns.POPULARITY,
                    DatabaseContract.TableColumns.VOTE_COUNT,
                    DatabaseContract.TableColumns.VOTE_AVG);

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_TV);
        onCreate(db);
    }
}
