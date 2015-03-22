package me.bigua.comiccollector.AbstBase.Models;

/**
 * Created by Bigua on 2/10/15.
 * bigua.kun@gmail.com
 */
public class comicAuthor {
    private Long _id;
    private Long author;
    private Long comic;

    public comicAuthor(Long author, Long comic) {
        this.author = author;
        this.comic = comic;
    }

    public Long getId() {
        return _id;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Long getComic() {
        return comic;
    }

    public void setComic(Long comic) {
        this.comic = comic;
    }
}
