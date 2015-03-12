package me.bigua.comiccollector.AbstBase;

import android.content.Context;
import android.util.Log;
import me.bigua.comiccollector.Models.Comic;

import java.util.Map;

/**
 * Created by Bigua on 3/12/15.
 * bigua.kun@gmail.com
 */
public class DataProxy {
    private Context context;

    public DataProxy(Context context) {
        this.context = context;
    }

    public Long saveComic(Map<String, String> raw) {

        Comic comic = new Comic();
        comicHandler ch = new comicHandler(this.context);
        Long id = ch.insertComic(comic);
        Log.wtf("id inserido", String.valueOf(id));
//        ch.insertComic(comic2);
//        ch.listComics();


        return null;
    }

    public Long saveAuthor() {
        return null;
    }
}
