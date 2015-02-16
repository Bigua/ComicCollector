package me.bigua.comiccollector.Models;

/**
 * Created by Bigua on 2/3/15.
 * bigua.kun@gmail.com
 */
public class Comic {

    private long _id;
    private String name;
    private int year;
    private String cover;
    private String publisher;
    private String barcode;
    private int number;


    public Comic() {

    }

    public Comic(String name, int year, String cover, String publisher, String barcode, int number) {
        this.name = name;
        this.year = year;
        this.cover = cover;
        this.publisher = publisher;
        this.barcode = barcode;
        this.number = number;
    }

}