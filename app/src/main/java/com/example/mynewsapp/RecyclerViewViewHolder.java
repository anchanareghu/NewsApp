package com.example.mynewsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
    TextView headLines_textView, source_textView;
    ImageView headlines_imageView;

    CardView cardView;

    public RecyclerViewViewHolder(@NonNull View itemView) {
        super(itemView);
        headLines_textView = (TextView) itemView.findViewById(R.id.head_lines_textView);
        source_textView = (TextView) itemView.findViewById(R.id.source_textView);
        headlines_imageView = (ImageView) itemView.findViewById(R.id.headlines_imageView);
        cardView = (CardView) itemView.findViewById(R.id.headlines_cardView);
    }

}
