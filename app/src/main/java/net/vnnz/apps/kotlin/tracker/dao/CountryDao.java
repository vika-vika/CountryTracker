package net.vnnz.apps.kotlin.tracker.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import net.vnnz.apps.kotlin.tracker.pojo.Item;

import java.util.List;

@Dao
public interface CountryDao {

    final String TABLE_NAME = "countries";

    @Query("SELECT * FROM " + TABLE_NAME)
    List<Item> getAll();

    @Query("SELECT COUNT(*) from " + TABLE_NAME)
    int countCountries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Item... countries);

    @Delete
    void delete(Item country);

}