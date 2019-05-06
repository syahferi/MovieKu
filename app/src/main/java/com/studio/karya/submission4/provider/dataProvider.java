package com.studio.karya.submission4.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.studio.karya.submission4.db.DML.ContentHelper;

import static com.studio.karya.submission4.db.DDL.DatabaseContract.AUTHORITY;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TABLE_MOVIE;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TABLE_TV;

public class dataProvider extends ContentProvider {

    private static final int CONTENT_MOVIE = 1;
    private static final int CONTENT_TV = 11;
    private static final int CONTENT_MOVIE_ID = 2;
    private static final int CONTENT_TV_ID = 22;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private ContentHelper contentHelper;

    static {
        // content://com.studio.karya.submission4/movie
        sUriMatcher.addURI(AUTHORITY, TABLE_MOVIE, CONTENT_MOVIE);
        // content://com.studio.karya.submission4/tv
        sUriMatcher.addURI(AUTHORITY, TABLE_TV, CONTENT_TV);

        // content://com.studio.karya.submission4/movie/id
        sUriMatcher.addURI(AUTHORITY, TABLE_MOVIE + "/#", CONTENT_MOVIE_ID);
        // content://com.studio.karya.submission4/tv/id
        sUriMatcher.addURI(AUTHORITY, TABLE_TV + "/#", CONTENT_TV_ID);
    }

    @Override
    public boolean onCreate() {
        contentHelper = ContentHelper.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        contentHelper.open();
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case CONTENT_MOVIE:
                Log.d("dataUR", sUriMatcher.match(uri) + "");
                cursor = contentHelper.queryProvider(TABLE_MOVIE);
                break;
            case CONTENT_TV:
                Log.d("dataUR", sUriMatcher.match(uri) + "");
                cursor = contentHelper.queryProvider(TABLE_TV);
                break;
            case CONTENT_MOVIE_ID:
                cursor = contentHelper.queryByIdProvider(uri.getLastPathSegment(), TABLE_MOVIE);
                break;
            case CONTENT_TV_ID:
                cursor = contentHelper.queryByIdProvider(uri.getLastPathSegment(), TABLE_TV);
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
