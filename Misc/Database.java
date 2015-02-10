package me.bigua.comiccollector.Misc;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Bigua on 2/3/15.
 */
public class Database extends SQLiteOpenHelper {

    private Context ctx;
    public Database(Context context){
        super(context,"name",null,1);
        this.ctx = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        AssetManager assets = this.ctx.getAssets();
        try {

            InputStream sss = assets.open("db.sql");
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.execSQL("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
