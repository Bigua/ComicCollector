package me.bigua.comiccollector.Models;

/**
 * Created by Bigua on 2/10/15.
 * bigua.kun@gmail.com
 */
public class comicGalaxy {
    private Long _id;
    private Long family;
    private Long comic;

    public comicGalaxy(Long family, Long comic) {
        this.family = family;
        this.comic = comic;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getFamily() {
        return family;
    }

    public void setFamily(Long family) {
        this.family = family;
    }

    public Long getComic() {
        return comic;
    }

    public void setComic(Long comic) {
        this.comic = comic;
    }
}
