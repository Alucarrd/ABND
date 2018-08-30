package com.petina.android.newsapp;

public class NewsArticle {
    private String _type;
    private String _sectionName;
    private String _publicationDate;
    private String _title;
    private String _author;
    private String _url;
    private String _sectionId;

    public NewsArticle(String type, String sectionName, String publicationDate, String title,String author, String url, String sectionId ){
        _type = type;
        _sectionName = sectionName;
        _sectionId = sectionId;
        _publicationDate = publicationDate;
        _title = title;
        _author = author;
        _url = url;

    }

    public String getType(){
        return _type;
    }

    public String getSectionName() {
        return _sectionName;
    }

    public String getSectionId(){
        return _sectionId;
    }
    public String getPublicationDate(){
        return _publicationDate;
    }


    public String getTitle(){
        return _title;
    }
    public String getAuthor(){
        return _author;
    }
    public String getURL(){
        return _url;
    }


}
