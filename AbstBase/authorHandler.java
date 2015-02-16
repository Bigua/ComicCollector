package me.bigua.comiccollector.AbstBase;

import android.content.Context;
import me.bigua.comiccollector.BaseHelper;
import me.bigua.comiccollector.Models.Author;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Bigua on 2/16/15.
 * bigua.kun@gmail.com
 */
public class authorHandler {
    private Context context;
    private BaseHelper baseHelper;

    public authorHandler(Context context) {
        this.context = context;
    }

    public Long insertAuthor(Author author) {
        baseHelper = new BaseHelper(this.context);
        return cupboard().withDatabase(
                baseHelper.getWritableDatabase())
                .put(author);
    }
}
