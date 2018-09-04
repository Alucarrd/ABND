package com.petina.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsArticleRecyclerViewAdapter  extends RecyclerView.Adapter<NewsArticleRecyclerViewAdapter.ViewHolder>  {
    private Context _context;
    private ArrayList<NewsArticle> _newsArticles;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView sectionNameTextView, sectionNameLabelTextView, titleTextView, contributorsTextView, publicationDateTextView, publicationDateLabelTextView;
        public ViewHolder(View view){
            super(view);
            sectionNameLabelTextView = view.findViewById(R.id.item_section_name_label);
            sectionNameTextView = view.findViewById(R.id.item_section_name);
            titleTextView = view.findViewById(R.id.item_title);
            contributorsTextView = view.findViewById(R.id.item_contributor);
            publicationDateLabelTextView = view.findViewById(R.id.item_publication_label);
            publicationDateTextView = view.findViewById(R.id.item_publication);
        }
    }

    //constructor for NewsArticleRecyclerViewAdapter
    public NewsArticleRecyclerViewAdapter(Context context, ArrayList<NewsArticle> vNewsArticles){
        _context = context;
        _newsArticles = vNewsArticles;
    }

    //access layout xml and return ViewHolder object
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_constraintlayout, parent, false);
        return new ViewHolder(view);
    }
    //bind ViewHolder with data
    @Override
    public void onBindViewHolder(ViewHolder vMyViewHolder, int position){
        final NewsArticle myItem = _newsArticles.get(position);
        vMyViewHolder.sectionNameTextView.setText(myItem.getSectionName());
        vMyViewHolder.titleTextView.setText(myItem.getTitle());
        if(myItem.getContributor().isEmpty()){
            vMyViewHolder.contributorsTextView.setVisibility(View.GONE);
        }else{
            vMyViewHolder.contributorsTextView.setVisibility(View.VISIBLE);
            String contributorTemplate = _context.getString(R.string.label_contributor);
            vMyViewHolder.contributorsTextView.setText(String.format(contributorTemplate, myItem.getContributor()));
        }

        if(myItem.getPublicationDate().isEmpty()){
            vMyViewHolder.publicationDateTextView.setVisibility(View.GONE);
            vMyViewHolder.publicationDateLabelTextView.setVisibility(View.GONE);
        }else{
            vMyViewHolder.publicationDateTextView.setVisibility(View.VISIBLE);
            vMyViewHolder.publicationDateLabelTextView.setVisibility(View.VISIBLE);
            vMyViewHolder.publicationDateTextView.setText(myItem.getPublicationDate());
        }
        //setup onclick listener to redirect clicker to actual news article web link
        vMyViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myURL = myItem.getURL();
                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                urlIntent.setData(Uri.parse(myURL));
                _context.startActivity(urlIntent);
            }
        });

    }
    //return the size for ArrayList object
    @Override
    public int getItemCount() {
        return _newsArticles.size();
    }






}
