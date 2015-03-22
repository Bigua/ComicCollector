package me.bigua.comiccollector.AbstBase.Handlers;

import android.content.Context;
import me.bigua.comiccollector.AbstBase.BaseMaker;
import me.bigua.comiccollector.AbstBase.Models.Comic;

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
        return cupboard().withDatabase(baseMaker.getWritableDatabase()).put(comic);
    }

    public List<Comic> List() {
        baseMaker = new BaseMaker(this.context);
        return cupboard().withDatabase(baseMaker.getWritableDatabase()).query(Comic.class).list();
    }
}
