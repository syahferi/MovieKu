package com.studio.karya.submission4.menu.fragment.content;

import com.studio.karya.submission4.api.repository.CallbackRepository;
import com.studio.karya.submission4.api.repository.Repository;
import com.studio.karya.submission4.model.ContentResponse;

class ContentPresenter {

    private ContentView view;
    private Repository repository;

    ContentPresenter(ContentView view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    void getContent(String type) {
        switch (type) {
            case "movie":
                repository.getDataMovie(new CallbackRepository<ContentResponse>() {
                    @Override
                    public void onDataLoaded(ContentResponse data) {
                        view.showLoading();
                        view.loadData(data);
                    }

                    @Override
                    public void onDataError(String message) {
                        view.showError(message);
                    }

                    @Override
                    public void onComplete() {
                        view.hideLoading();
                    }
                });
                break;
            case "tv":
                repository.getDataTv(new CallbackRepository<ContentResponse>() {
                    @Override
                    public void onDataLoaded(ContentResponse data) {
                        view.showLoading();
                        view.loadData(data);
                    }

                    @Override
                    public void onDataError(String message) {
                        view.showError(message);
                    }

                    @Override
                    public void onComplete() {
                        view.hideLoading();
                    }
                });
                break;
        }
    }
}
