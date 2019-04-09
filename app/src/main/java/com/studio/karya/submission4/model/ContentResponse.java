package com.studio.karya.submission4.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ContentResponse {

    @SerializedName("results")
    private List<Content> contentList;

    public List<Content> getContentList() {
        return contentList;
    }
}
