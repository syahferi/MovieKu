package com.studio.karya.submission4.utils;

import android.database.Cursor;

import com.studio.karya.submission4.model.Content;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.BACKDROP_POSTER;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.ID_CONTENT;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.OVERVIEW;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.POPULARITY;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.POSTER;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.RELEASE_DATE;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.TITLE;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.VOTE_AVG;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.VOTE_COUNT;

public class MappingHelper {

    public static ArrayList<Content> mapCursorToArrayList(Cursor contentCursor) {
        ArrayList<Content> contentList = new ArrayList<>();

        while (contentCursor.moveToNext()) {
            int id = contentCursor.getInt(contentCursor.getColumnIndexOrThrow(ID_CONTENT));
            String title = contentCursor.getString(contentCursor.getColumnIndexOrThrow(TITLE));
            String overview = contentCursor.getString(contentCursor.getColumnIndexOrThrow(OVERVIEW));
            String releaseDate = contentCursor.getString(contentCursor.getColumnIndexOrThrow(RELEASE_DATE));
            String poster = contentCursor.getString(contentCursor.getColumnIndexOrThrow(POSTER));
            String backdropPoster = contentCursor.getString(contentCursor.getColumnIndexOrThrow(BACKDROP_POSTER));
            String popularity = contentCursor.getString(contentCursor.getColumnIndexOrThrow(POPULARITY));
            String voteCount = contentCursor.getString(contentCursor.getColumnIndexOrThrow(VOTE_COUNT));
            String voteAvg = contentCursor.getString(contentCursor.getColumnIndexOrThrow(VOTE_AVG));
            contentList.add(new Content(id, voteCount, voteAvg, title, popularity, poster, backdropPoster, overview, releaseDate));
        }
        return contentList;
    }
}
