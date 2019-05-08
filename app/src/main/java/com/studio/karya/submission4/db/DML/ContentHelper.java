package com.studio.karya.submission4.db.DML;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.studio.karya.submission4.db.DDL.DatabaseHelper;
import com.studio.karya.submission4.model.Content;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TABLE_MOVIE;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.BACKDROP_POSTER;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.ID_CONTENT;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.OVERVIEW;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.POPULARITY;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.POSTER;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.RELEASE_DATE;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.TITLE;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.VOTE_AVG;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.VOTE_COUNT;

public class ContentHelper {

    private static DatabaseHelper databaseHelper;
    private static ContentHelper INSTANCE;
    private static SQLiteDatabase database;

    private ContentHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static ContentHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ContentHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (database.isOpen()) {
            database.close();
        }
    }

    //crud
    //Read
    public ArrayList<Content> getAllContent(String database_table) {
        ArrayList<Content> contents = new ArrayList<>();
        Cursor cursor = database.query(database_table,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        Content content;
        if (cursor.getCount() > 0) {
            do {
                content = new Content();
                if (database_table.equals(TABLE_MOVIE)) {
                    content.setTitleFilm(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                    content.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                } else {
                    content.setTitleTv(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                    content.setFirstAirDateTv(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));

                }
                content.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID_CONTENT)));
                content.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                content.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                content.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP_POSTER)));
                content.setPopularity(cursor.getString(cursor.getColumnIndexOrThrow(POPULARITY)));
                content.setVoteCount(cursor.getString(cursor.getColumnIndexOrThrow(VOTE_COUNT)));
                content.setVoteAverage(cursor.getString(cursor.getColumnIndexOrThrow(VOTE_AVG)));

                contents.add(content);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return contents;
    }

    public boolean hasContent(String table, int id_content) {
        String selection = "id_content = ?";
        boolean hasResult;
        Cursor cursor = database.query(table, null,
                selection,
                new String[]{String.valueOf(id_content)},
                null,
                null,
                _ID + " ASC",
                null);
        hasResult = cursor.getCount() > 0;
        cursor.close();
        return hasResult;
    }

    //create
    public void insertContent(Content content, String table) {
        ContentValues contentValues = new ContentValues();

        if (table.equals(TABLE_MOVIE)) {
            contentValues.put(TITLE, content.getTitleFilm());
            contentValues.put(RELEASE_DATE, content.getReleaseDate());
        } else {
            contentValues.put(TITLE, content.getTitleTv());
            contentValues.put(RELEASE_DATE, content.getFirstAirDateTv());
        }
        contentValues.put(ID_CONTENT, content.getId());
        contentValues.put(OVERVIEW, content.getOverview());
        contentValues.put(POSTER, content.getPosterPath());
        contentValues.put(BACKDROP_POSTER, content.getBackdropPath());
        contentValues.put(POPULARITY, content.getPopularity());
        contentValues.put(VOTE_COUNT, content.getVoteCount());
        contentValues.put(VOTE_AVG, content.getVoteAverage());
        database.insert(table, null, contentValues);
    }

    //delete
    public void deleteContent(int id, String table) {
        database.delete(table, ID_CONTENT + "= '" + id + "'", null);
    }

    //content provider
    //movie
    public Cursor queryByIdProvider(String id, String table) {
        return database.query(table, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryProvider(String table) {
        return database.query(table
                , null
                , null
                , null
                , null
                , null
                , _ID + " ASC");
    }

    /*public long insertProvider(ContentValues values) {
        return database.insert(TABLE_MOVIE, null, values);
    }

    public int updateProvider(String id, ContentValues values) {
        return database.update(TABLE_MOVIE, values, _ID + " = ?", new String[]{id});
    }

    public int deleteProvider(String id) {
        return database.delete(TABLE_MOVIE, _ID + " = ?", new String[]{id});
    }*/
}
