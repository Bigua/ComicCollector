package me.bigua.comiccollector.AbstBase;

import android.content.Context;
import me.bigua.comiccollector.Models.Galaxy;

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

    public Long insertFamily(Galaxy galaxy) {
        baseMaker = new BaseMaker(this.context);
        return cupboard().withDatabase(
                baseMaker.getWritableDatabase())
                .put(galaxy);
    }

    public List<Galaxy> listFamily() {
        baseMaker = new BaseMaker(this.context);
        List<Galaxy> galaxy = cupboard().withDatabase(
                baseMaker.getWritableDatabase())
                .query(Galaxy.class).list();
        int i = galaxy.size();
        return galaxy;
    }


}
