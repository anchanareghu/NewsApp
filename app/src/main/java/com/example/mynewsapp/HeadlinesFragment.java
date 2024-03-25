package com.example.mynewsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Models.HeadLines;
import Models.NewsApi;

public class HeadlinesFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ProgressBar progressBar;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headlines, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_headlines);
        progressBar = view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ApiRequestManager apiRequestManager = new ApiRequestManager(HeadlinesFragment.this.getContext());
                apiRequestManager.getNewsHeadLines(listener, "general", newText);
                return true;
            }
        });
        ApiRequestManager apiRequestManager = new ApiRequestManager(this.getContext());
        apiRequestManager.getNewsHeadLines(listener, "general", null);
        return view;
    }

    private final OnFetchDataListener<NewsApi> listener = new OnFetchDataListener<NewsApi>() {
        @Override
        public void onFetchData(List<HeadLines> headLinesList, String message) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(HeadlinesFragment.this.getContext(), 1));
            adapter = new RecyclerViewAdapter(headLinesList, HeadlinesFragment.this.getContext());
            recyclerView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onError(String message) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
}