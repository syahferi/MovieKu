package com.studio.karya.submission4.api.repository;

import com.studio.karya.submission4.api.MyRetrofit;
import com.studio.karya.submission4.model.ContentResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.studio.karya.submission4.BuildConfig.API_KEY;

public class Repository {

    public void getDataMovie(final CallbackRepository<ContentResponse> callback) {
        MyRetrofit.getApiService()
                .getMovie(API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContentResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ContentResponse dataResponse) {
                        callback.onDataLoaded(dataResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        callback.onComplete();
                    }
                });
    }

    public void getDataTv(final CallbackRepository<ContentResponse> callback) {
        MyRetrofit.getApiService()
                .getTv(API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContentResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ContentResponse dataResponse) {
                        callback.onDataLoaded(dataResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        callback.onComplete();
                    }
                });
    }
}
