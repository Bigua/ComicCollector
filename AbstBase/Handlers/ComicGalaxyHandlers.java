package me.bigua.comiccollector.AbstBase.Handlers;

import android.content.Context;
import me.bigua.comiccollector.AbstBase.BaseMaker;
import me.bigua.comiccollector.AbstBase.Models.comicGalaxy;

import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Bigua on 2/16/15.
 * bigua.kun@gmail.com
 */
public class ComicGalaxyHandlers {

    private Context context;
    private BaseMaker baseMaker;

    public ComicGalaxyHandlers(Context context) {
        this.context = context;
    }

    public Long insertComicGalaxy(comicGalaxy comicGalaxy) {
        baseMaker = new BaseMaker(this.context);
        return cupboard().withDatabase(baseMaker.getWritableDatabase()).put(comicGalaxy);
    }

    public List<ComicGalaxyHandlers> List() {
        baseMaker = new BaseMaker(this.context);
        return cupboard().withDatabase(baseMaker.getWritableDatabase()).query(ComicGalaxyHandlers.class).list();
    }

}
