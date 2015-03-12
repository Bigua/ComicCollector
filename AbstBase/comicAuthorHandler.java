package me.bigua.comiccollector.AbstBase;

import android.content.Context;
import me.bigua.comiccollector.Models.comicAuthor;

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
        return cupboard().withDatabase(
                baseMaker.getWritableDatabase())
                .put(comicAuthor);
    }

}
