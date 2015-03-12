package me.bigua.comiccollector.AbstBase;

import android.content.Context;
import me.bigua.comiccollector.Models.Comic;

import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Bigua on 2/15/15.
 * bigua.kun@gmail.com
 */
public class comicHandler {
    private Context context;
    private BaseMaker baseMaker;

    public comicHandler(Context context) {
        this.context = context;
    }


    public Long insertComic(Comic comic) {
        baseMaker = new BaseMaker(this.context);
        return cupboard().withDatabase(
                baseMaker.getWritableDatabase())
                .put(comic);
    }

    public List<Comic> listComics() {
        baseMaker = new BaseMaker(this.context);
        List<Comic> comics = cupboard().withDatabase(
                baseMaker.getWritableDatabase())
                .query(Comic.class).list();
        int i = comics.size();
        return comics;
    }
}
