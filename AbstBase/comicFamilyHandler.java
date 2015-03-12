package me.bigua.comiccollector.AbstBase;

import android.content.Context;
import me.bigua.comiccollector.Models.comicFamily;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Bigua on 2/16/15.
 * bigua.kun@gmail.com
 */
public class comicFamilyHandler {

    private Context context;
    private BaseMaker baseMaker;

    public comicFamilyHandler(Context context) {
        this.context = context;
    }

    public Long insertComicFamily(comicFamily comicFamily) {
        baseMaker = new BaseMaker(this.context);
        return cupboard().withDatabase(
                baseMaker.getWritableDatabase())
                .put(comicFamily);
    }

}
