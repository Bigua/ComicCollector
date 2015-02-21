package me.bigua.comiccollector.Models;

/**
 * Created by Bigua on 2/3/15.
 * bigua.kun@gmail.com
 */
public class Comic {

    private Long _id;
    private String name;
    private int year;
    private String cover;
    private String publisher;
    private String barcode;
    private String type;
    private String lang;
    private int number;
    private Boolean complete;

    public Comic() {
    }

    public Comic(String name,
                 int year,
                 String cover,
                 String publisher,
                 String barcode,
                 int number,
                 String type,
                 String lang,
                 Boolean complete) {
        this.name = name;
        this.year = year;
        this.cover = cover;
        this.publisher = publisher;
        this.barcode = barcode;
        this.number = number;
        this.lang = lang;
        this.type = type;
        this.complete = complete;
    }


    public Long getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}