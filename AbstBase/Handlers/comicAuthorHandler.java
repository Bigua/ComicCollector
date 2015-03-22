package me.bigua.comiccollector.AbstBase.Handlers;

import android.content.Context;
import me.bigua.comiccollector.AbstBase.BaseMaker;
import me.bigua.comiccollector.AbstBase.Models.comicAuthor;

import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Bigua on 2/16/15.
 * bigua.kun@gmail.com
 */
public class comicAuthorHandler {

    private Context context;
    private BaseMaker baseMaker;

    public comicAuthorHandler(Context context) {
        this.context = context;
    }

    public Long insertComicAuthor(comicAuthor comicAuthor) {
        baseMaker = new BaseMaker(this.context);
        return cupboard().withDatabase(baseMaker.getWritableDatabase()).put(comicAuthor);
    }

    public List<comicAuthor> List() {
        baseMaker = new BaseMaker(this.context);
        return cupboard().withDatabase(baseMaker.getWritableDatabase()).query(comicAuthor.class).list();
    }

}
