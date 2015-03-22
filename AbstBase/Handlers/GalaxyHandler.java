package me.bigua.comiccollector.AbstBase.Handlers;

import android.content.Context;
import me.bigua.comiccollector.AbstBase.BaseMaker;
import me.bigua.comiccollector.AbstBase.Models.Galaxy;

import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Bigua on 2/16/15.
 * bigua.kun@gmail.com
 */
public class GalaxyHandler {
    private Context context;
    private BaseMaker baseMaker;

    public GalaxyHandler(Context context) {
        this.context = context;
    }

    public Long insertGalaxy(Galaxy galaxy) {
        baseMaker = new BaseMaker(this.context);
        return cupboard().withDatabase(baseMaker.getWritableDatabase()).put(galaxy);
    }

    public List<Galaxy> List() {
        baseMaker = new BaseMaker(this.context);
        return cupboard().withDatabase(baseMaker.getWritableDatabase()).query(Galaxy.class).list();

    }


}
