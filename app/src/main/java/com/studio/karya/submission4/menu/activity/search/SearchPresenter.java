package com.studio.karya.submission4.menu.activity.search;

import com.studio.karya.submission4.api.repository.CallbackRepository;
import com.studio.karya.submission4.api.repository.Repository;
import com.studio.karya.submission4.model.ContentResponse;

class SearchPresenter {

    private SearchView view;
    private Repository repository;
    private String type;

    SearchPresenter(SearchView view, Repository repository, String type) {
        this.view = view;
        this.repository = repository;
        this.type = type;
    }

    void searchContent(String searchContent) {
        switch (type) {
            case "movie":
                repository.searchMovie(searchContent, new CallbackRepository<ContentResponse>() {
                    @Override
                    public void onDataLoaded(ContentResponse data) {
                        view.showData(data);
                    }

                    @Override
                    public void onDataError(String message) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                break;
            case "tv":
                repository.searchTv(searchContent, new CallbackRepository<ContentResponse>() {
                    @Override
                    public void onDataLoaded(ContentResponse data) {
                        view.showData(data);
                    }

                    @Override
                    public void onDataError(String message) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                break;
        }
    }
}
