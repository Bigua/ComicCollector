package me.bigua.comiccollector.AbstBase;

import android.content.Context;
import android.util.Log;
import me.bigua.comiccollector.BaseHelper;
import me.bigua.comiccollector.Models.Comic;

import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Bigua on 2/15/15.
 * bigua.kun@gmail.com
 */
public class comicHandler {
    private Context context;
    private BaseHelper baseHelper;

    public comicHandler(Context context) {
        this.context = context;
    }


    public Long insertComic(Comic comic) {
        baseHelper = new BaseHelper(this.context);
        return cupboard().withDatabase(
                baseHelper.getWritableDatabase())
                .put(comic);
    }

    public List<Comic> listComics() {
        baseHelper = new BaseHelper(this.context);
        List<Comic> comics = cupboard().withDatabase(
                baseHelper.getWritableDatabase())
                .query(Comic.class).list();
        int i = comics.size();
        Log.wtf("total", String.valueOf(i));
        return comics;
    }
}
