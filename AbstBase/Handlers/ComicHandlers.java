package me.bigua.comiccollector.AbstBase.Handlers;

import android.content.ContentValues;
import android.content.Context;
import me.bigua.comiccollector.AbstBase.BaseMaker;
import me.bigua.comiccollector.AbstBase.Models.Comic;

import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Bigua on 2/15/15.
 * bigua.kun@gmail.com
 */
public class ComicHandlers {
    private Context context;
    private BaseMaker baseMaker;

    public ComicHandlers(Context context) {
        this.context = context;
    }


    public int updateComic(String field, String value, Long id) {
        baseMaker = new BaseMaker(this.context);
        ContentValues values = new ContentValues(1);
        values.put(field, value);
// update all books where the title is 'android'
        int status = cupboard().withDatabase(baseMaker.getWritableDatabase()).update(Comic.class, values, "_id = ?", id.toString());
        return status;
    }

    public Long insertComic(Comic comic) {
        baseMaker = new BaseMaker(this.context);
        return cupboard().withDatabase(baseMaker.getWritableDatabase()).put(comic);
    }

    public List<Comic> List() {
        baseMaker = new BaseMaker(this.context);
        return cupboard().withDatabase(baseMaker.getWritableDatabase()).query(Comic.class).list();
    }

}
