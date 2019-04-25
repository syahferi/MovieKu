package com.studio.karya.submission4.api.repository;

public interface CallbackRepository<T> {
    void onDataLoaded(T data);

    void onDataError(String message);

    void onComplete();
}
