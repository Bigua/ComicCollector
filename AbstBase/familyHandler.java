package me.bigua.comiccollector.AbstBase;

import android.content.Context;
import me.bigua.comiccollector.Models.BaseHelper;
import me.bigua.comiccollector.Models.Family;

import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Bigua on 2/16/15.
 * bigua.kun@gmail.com
 */
public class familyHandler {
    private Context context;
    private BaseHelper baseHelper;

    public familyHandler(Context context) {
        this.context = context;
    }

    public Long insertFamily(Family family) {
        baseHelper = new BaseHelper(this.context);
        return cupboard().withDatabase(
                baseHelper.getWritableDatabase())
                .put(family);
    }

    public List<Family> listFamily() {
        baseHelper = new BaseHelper(this.context);
        List<Family> family = cupboard().withDatabase(
                baseHelper.getWritableDatabase())
                .query(Family.class).list();
        int i = family.size();
        return family;
    }


}
