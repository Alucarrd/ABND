package com.petina.android.newsapp;

public class NewsArticle {
    private String _type;
    private String _sectionName;
    private String _publicationDate;
    private String _title;
    private String _contributor;
    private String _url;

    //constructor for the NewsArticle object
    public NewsArticle(String type, String sectionName, String publicationDate, String title, String contributor, String url) {
        _type = type;
        _sectionName = sectionName;
        _publicationDate = publicationDate;
        _title = title;
        _contributor = contributor;
        _url = url;

    }

    //getter for type
    public String getType() {
        return _type;
    }

    //getter for section name
    public String getSectionName() {
        return _sectionName;
    }

    //getter for publication date
    public String getPublicationDate() {
        return _publicationDate;
    }

    //getter for title
    public String getTitle() {
        return _title;
    }

    //getter for contributor
    public String getContributor() {
        return _contributor;
    }

    //getter for url
    public String getURL() {
        return _url;
    }


}
