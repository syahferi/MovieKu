package com.studio.karya.submission4.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.studio.karya.submission4.db.DDL.DatabaseContract;

import static android.provider.BaseColumns._ID;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.getColumnInt;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.getColumnString;

public class Content implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String titleTv;

    @SerializedName("vote_count")
    private String voteCount;

    @SerializedName("vote_average")
    private String voteAverage;

    @SerializedName("title")
    private String titleFilm;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("first_air_date")
    private String firstAirDateTv;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleTv() {
        return titleTv;
    }

    public void setTitleTv(String titleTv) {
        this.titleTv = titleTv;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitleFilm() {
        return titleFilm;
    }

    public void setTitleFilm(String titleFilm) {
        this.titleFilm = titleFilm;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getFirstAirDateTv() {
        return firstAirDateTv;
    }

    public void setFirstAirDateTv(String firstAirDateTv) {
        this.firstAirDateTv = firstAirDateTv;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.titleTv);
        dest.writeString(this.voteCount);
        dest.writeString(this.voteAverage);
        dest.writeString(this.titleFilm);
        dest.writeString(this.popularity);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeString(this.firstAirDateTv);
    }

    public Content() {
    }

    //constructor movie
    public Content(int id, String voteCount, String voteAverage, String titleFilm, String popularity, String posterPath, String backdropPath, String overview, String releaseDate) {
        this.id = id;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.titleFilm = titleFilm;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public Content(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.voteCount = getColumnString(cursor, DatabaseContract.TableColumns.VOTE_COUNT);
        this.voteAverage = getColumnString(cursor, DatabaseContract.TableColumns.VOTE_AVG);
        this.titleFilm = getColumnString(cursor, DatabaseContract.TableColumns.TITLE);
        this.popularity = getColumnString(cursor, DatabaseContract.TableColumns.POPULARITY);
        this.posterPath = getColumnString(cursor, DatabaseContract.TableColumns.POSTER);
        this.backdropPath = getColumnString(cursor, DatabaseContract.TableColumns.BACKDROP_POSTER);
        this.overview = getColumnString(cursor, DatabaseContract.TableColumns.OVERVIEW);
        this.releaseDate = getColumnString(cursor, DatabaseContract.TableColumns.RELEASE_DATE);
    }

    public Content(Parcel in) {
        this.id = in.readInt();
        this.titleTv = in.readString();
        this.voteCount = in.readString();
        this.voteAverage = in.readString();
        this.titleFilm = in.readString();
        this.popularity = in.readString();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.firstAirDateTv = in.readString();
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel source) {
            return new Content(source);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };
}
