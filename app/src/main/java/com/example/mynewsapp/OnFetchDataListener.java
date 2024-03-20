package com.example.mynewsapp;

import java.util.List;

import Models.HeadLines;

public interface OnFetchDataListener<NewsApi> {
    void onFetchData(List<HeadLines> headLinesList, String message);
    void onError(String message);
}
