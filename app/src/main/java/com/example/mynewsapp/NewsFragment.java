package com.example.mynewsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Models.HeadLines;
import Models.NewsApi;


public class NewsFragment extends Fragment implements View.OnClickListener {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ProgressBar progressBar;
    Button general_btn, business_btn, sports_btn, science_btn, health_btn, entertainment_btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_news);

        general_btn = view.findViewById(R.id.general_btn);
        general_btn.setOnClickListener(this);

        business_btn = view.findViewById(R.id.business_btn);
        business_btn.setOnClickListener(this);
        business_btn.performClick();

        entertainment_btn = view.findViewById(R.id.entertainment_btn);
        entertainment_btn.setOnClickListener(this);

        health_btn = view.findViewById(R.id.health_btn);
        health_btn.setOnClickListener(this);

        science_btn = view.findViewById(R.id.science_btn);
        science_btn.setOnClickListener(this);

        sports_btn = view.findViewById(R.id.sports_btn);
        sports_btn.setOnClickListener(this);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        return view;
    }

    public final OnFetchDataListener<NewsApi> listener = new OnFetchDataListener<NewsApi>() {
        @Override
        public void onFetchData(List<HeadLines> headLinesList, String message) {
            loadData(headLinesList);
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

    public void loadData(List<HeadLines> headLinesList) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(NewsFragment.this.getContext(), 1));
        adapter = new RecyclerViewAdapter(headLinesList, NewsFragment.this.getContext());
        recyclerView.setAdapter(adapter);
    }

    private Button lastClickedButton = null;

    @Override
    public void onClick(View view) {
        Button currentButton = (Button) view;
        if (lastClickedButton != null) {
            lastClickedButton.setTextColor(getResources().getColor(R.color.white));
        }
        currentButton.setTextColor(getResources().getColor(R.color.sage));
        lastClickedButton = currentButton;

        String category = currentButton.getText().toString();
        ApiRequestManager apiRequestManager = new ApiRequestManager(view.getContext());
        apiRequestManager.getNewsHeadLines(listener, category, null);
    }
}