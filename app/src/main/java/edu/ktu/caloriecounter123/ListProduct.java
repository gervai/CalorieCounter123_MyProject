package edu.ktu.caloriecounter123;

import android.widget.Filter;
import android.widget.Filterable;

import java.util.Comparator;

public class ListProduct {
    private String title;
    private int imageId;
    private String description;

    public ListProduct(){
    }

    public ListProduct(String title, int imageId, String description){
        this.title = title;
        this.imageId = imageId;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static final Comparator<ListProduct> By_TITLE_ASCENDING = new Comparator<ListProduct>() {
        @Override
        public int compare(ListProduct o1, ListProduct o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    public static final Comparator<ListProduct> By_TITLE_DESCENDING = new Comparator<ListProduct>() {
        @Override
        public int compare(ListProduct o1, ListProduct o2) {
            return o2.getTitle().compareTo(o1.getTitle());
        }
    };
}
