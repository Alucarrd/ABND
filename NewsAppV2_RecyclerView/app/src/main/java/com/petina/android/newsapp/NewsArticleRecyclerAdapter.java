package com.petina.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsArticleRecyclerAdapter extends RecyclerView.Adapter<NewsArticleRecyclerAdapter.NewsArticleViewHolder> {
    private Context _context;
    private final List<NewsArticle> _newsArticles;

    public class NewsArticleViewHolder extends RecyclerView.ViewHolder{
        public TextView sectionNameTextView, sectionNameLabelTextView, titleTextView, contributorTextView, publicationTextView, publicationLabelTextView;

        public NewsArticleViewHolder(View view){
            super(view);
            sectionNameLabelTextView = (TextView) view.findViewById(R.id.item_section_name_label);
            sectionNameTextView = (TextView) view.findViewById(R.id.item_section_name);
            titleTextView = (TextView) view.findViewById(R.id.item_title);
            contributorTextView = (TextView) view.findViewById(R.id.item_contributor);
            publicationLabelTextView = (TextView) view.findViewById(R.id.item_publication_label);
            publicationTextView = (TextView) view.findViewById(R.id.item_publication);
        }

    }

    public NewsArticleRecyclerAdapter(Context context, List<NewsArticle> newsArticles){

        _context = context;
        _newsArticles = newsArticles;
    }

    @Override
    public int getItemCount(){
        return _newsArticles.size();
    }

    @Override
    public NewsArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_constraintlayout, parent, false);
        return new NewsArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsArticleViewHolder vMyViewHolder, int position){
        final NewsArticle myItem = _newsArticles.get(position);
        vMyViewHolder.sectionNameTextView.setText(myItem.getSectionName());
        vMyViewHolder.titleTextView.setText(myItem.getTitle());
        if (myItem.getContributor().equalsIgnoreCase("")) {
            vMyViewHolder.contributorTextView.setVisibility(View.GONE);

        } else {
            String contributorTemplate = _context.getString(R.string.label_contributor);
            vMyViewHolder.contributorTextView.setVisibility(View.VISIBLE);
            vMyViewHolder.contributorTextView.setText(String.format(contributorTemplate, myItem.getContributor()));
        }

        if (myItem.getPublicationDate().equalsIgnoreCase("")) {
            vMyViewHolder.publicationTextView.setVisibility(View.GONE);
            vMyViewHolder.publicationLabelTextView.setVisibility(View.GONE);
        } else {
            vMyViewHolder.publicationTextView.setVisibility(View.VISIBLE);
            vMyViewHolder.publicationLabelTextView.setVisibility(View.VISIBLE);
            vMyViewHolder.publicationTextView.setText(getMyDate(myItem.getPublicationDate()));
        }

        vMyViewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String myURL = myItem.getURL();
                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                urlIntent.setData(Uri.parse(myURL));
                _context.startActivity(urlIntent);
            }

        });

    }


    /**
     * Takes in the ISO 8601 format date and convert to MM/dd/yyyy format
     *
     * @param myDate String format of publication date returned from the guardian news api
     * @return String of MM/dd/yyyy
     */
    public String getMyDate(String myDate) {

        if (!myDate.isEmpty()) {
            Log.v("adapter", myDate);
            try {
                Date date = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSZ")).parse(myDate.replaceAll("Z$", "+0000"));
                return new SimpleDateFormat("MM/dd/yyyy").format(date);
            } catch (ParseException e) {
                return "";
            }

        } else
            return "";

    }
}

