package com.studio.karya.submission4.menu.fragment.content;

import com.studio.karya.submission4.model.ContentResponse;

public interface ContentView {
    void loadData(ContentResponse data);

    void showLoading();

    void hideLoading();

    void showError(String error);
}
