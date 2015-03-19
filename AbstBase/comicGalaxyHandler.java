package me.bigua.comiccollector.AbstBase;

import android.content.Context;
import me.bigua.comiccollector.Models.comicGalaxy;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Bigua on 2/16/15.
 * bigua.kun@gmail.com
 */
public class comicGalaxyHandler {

    private Context context;
    private BaseMaker baseMaker;

    public comicGalaxyHandler(Context context) {
        this.context = context;
    }

    public Long insertComicGalaxy(comicGalaxy comicGalaxy) {
        baseMaker = new BaseMaker(this.context);
        return cupboard().withDatabase(
                baseMaker.getWritableDatabase())
                .put(comicGalaxy);
    }

}
