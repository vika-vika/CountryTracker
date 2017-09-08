package net.vnnz.apps.kotlin.tracker.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import net.vnnz.apps.kotlin.tracker.pojo.Item;

@Database(entities = {Item.class}, version = 1)

public abstract class CountriesDatabase extends RoomDatabase {

    private static CountriesDatabase  INSTANCE;

    public abstract CountryDao countryDao();

    public static CountriesDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), CountriesDatabase.class, "database")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}