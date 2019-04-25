package com.studio.karya.submission4.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContentResponse {

    @SerializedName("results")
    private ArrayList<Content> contentList;

    public ArrayList<Content> getContentList() {
        return contentList;
    }
}
