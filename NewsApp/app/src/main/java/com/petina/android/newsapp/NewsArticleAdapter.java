package com.petina.android.newsapp;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

public class NewsArticleAdapter extends ArrayAdapter<NewsArticle> {

    public NewsArticleAdapter(Activity context, List<NewsArticle> newsArticles){
        super(context, 0, newsArticles);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_constraintlayout, parent, false);
        }

        NewsArticle myItem = getItem(position);
        TextView sectionName = (TextView) listItemView.findViewById(R.id.item_section_name);
        sectionName.setText(myItem.getSectionName());

        TextView title = (TextView) listItemView.findViewById(R.id.item_title);
        title.setText(myItem.getTitle());

        TextView contributor = (TextView) listItemView.findViewById(R.id.item_contributor);
        TextView contributor_label = (TextView) listItemView.findViewById(R.id.item_contributor_label);

        if(myItem.getAuthor().equalsIgnoreCase("")) {
            contributor.setVisibility(View.GONE);
            contributor_label.setVisibility(View.GONE);
        }else {
            contributor.setText(myItem.getAuthor());
        }

        TextView publication = (TextView) listItemView.findViewById(R.id.item_publication);
        TextView publication_label = (TextView) listItemView.findViewById(R.id.item_publication_label);

        if(myItem.getPublicationDate().equalsIgnoreCase("")) {
            publication.setVisibility(View.GONE);
            publication_label.setVisibility(View.GONE);
        }else {

            publication.setText(getMyDate(myItem.getPublicationDate()));
        }

        return listItemView;


    }

    public String getMyDate(String myDate) {

        if (!myDate.isEmpty()) {
            try {
                Date date = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).parse(myDate.replaceAll("Z$", "+0000"));
                return new SimpleDateFormat("MM/dd/yyyy").format(date);
            } catch (ParseException e) {
                return "";
            }

        }else
            return "";

    }



}






