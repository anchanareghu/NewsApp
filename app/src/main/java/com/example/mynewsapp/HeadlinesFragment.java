package com.example.mynewsapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import Models.HeadLines;
import Models.NewsApi;

public class HeadlinesFragment extends Fragment {
    RecyclerView recyclerView;
    HeadLinesRecyclerViewAdapter adapter;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headlines, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_headlines);
        progressBar = view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        ApiRequestManager apiRequestManager = new ApiRequestManager(this.getContext());
        apiRequestManager.getNewsHeadLines(listener, "general", null);
        return view;
    }

    private final OnFetchDataListener<NewsApi> listener = new OnFetchDataListener<NewsApi>() {
        @Override
        public void onFetchData(List<HeadLines> headLinesList, String message) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(HeadlinesFragment.this.getContext(), 1));
            adapter = new HeadLinesRecyclerViewAdapter(headLinesList, HeadlinesFragment.this.getContext());
            recyclerView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onError(String message) {
        }
    };
}