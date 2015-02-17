package me.bigua.comiccollector.AbstBase;

import android.content.Context;
import me.bigua.comiccollector.BaseHelper;
import me.bigua.comiccollector.Models.comicFamily;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Bigua on 2/16/15.
 * bigua.kun@gmail.com
 */
public class comicFamilyHandler {

    private Context context;
    private BaseHelper baseHelper;

    public comicFamilyHandler(Context context) {
        this.context = context;
    }

    public Long insertComicFamily(comicFamily comicFamily) {
        baseHelper = new BaseHelper(this.context);
        return cupboard().withDatabase(
                baseHelper.getWritableDatabase())
                .put(comicFamily);
    }

}
