package me.bigua.comiccollector.AbstBase.Models;

/**
 * Created by Bigua on 2/5/15.
 * bigua.kun@gmail.com
 */
public class Author {
    private Long _id;
    private String name;
    private String type;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
