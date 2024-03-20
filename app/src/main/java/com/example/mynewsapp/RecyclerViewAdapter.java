package com.example.mynewsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import Models.HeadLines;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewViewHolder> {
    List<HeadLines> headlines;
    Context context;

    public RecyclerViewAdapter(List<HeadLines> headlines, Context context) {
        this.headlines = headlines;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_headline, parent, false);
        return new RecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {
        if (headlines.get(position).getTitle() != null) {
            holder.headLines_textView.setText(headlines.get(position).getTitle());
        }
        if (headlines.get(position).getSource().getName() != null) {
            holder.source_textView.setText(headlines.get(position).getSource().getName());
        }
        if (headlines.get(position).getUrlToImage() != null) {
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.headlines_imageView);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                int position = holder.getAdapterPosition();
                intent.putExtra("url", headlines.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
