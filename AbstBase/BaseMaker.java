package me.bigua.comiccollector.AbstBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import me.bigua.comiccollector.Models.*;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Bigua on 2/10/15.
 * bigua.kun@gmail.com
 */
public class BaseMaker extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "comicBase.db";
    private static final int DATABASE_VERSION = 1;

    public BaseMaker(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static {
        cupboard().register(Comic.class);
        cupboard().register(Author.class);
        cupboard().register(comicAuthor.class);
        cupboard().register(comicFamily.class);
        cupboard().register(Family.class);
    }

    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }
}
