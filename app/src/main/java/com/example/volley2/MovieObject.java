package com.example.volley2;

import java.util.Comparator;

public class MovieObject {
    String title,rating,description,url;


    public MovieObject(){

    }
    public MovieObject(String title,String rating,String description,String url){
        this.title= title;
        this.rating = rating;
        this.description = description;
        this.url = url;
    }
    public static Comparator<MovieObject> comparatorAZ = new Comparator<MovieObject>() {
        @Override
        public int compare(MovieObject o1, MovieObject o2) {

            return o1.getTitle().compareTo(o2.getTitle());
        }
    };
    public static Comparator<MovieObject> comparatorZA = new Comparator<MovieObject>() {
        @Override
        public int compare(MovieObject o1, MovieObject o2) {

            return o2.getTitle().compareTo(o1.getTitle());
        }
    };
    public static Comparator<MovieObject> comparatorRatings = new Comparator<MovieObject>() {
        @Override
        public int compare(MovieObject o1, MovieObject o2) {
            Float input1 = Float.parseFloat(o1.getRating());
            Float input2 = Float.parseFloat(o2.getRating());

            return input2.intValue() - input1.intValue();
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
