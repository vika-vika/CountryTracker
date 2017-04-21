package net.vnnz.apps.kotlin.tracker;

/*
* Simple data Java class
* to check Kotlin -Java cross calling
* */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import net.vnnz.apps.kotlin.tracker.pojo.Item;
import net.vnnz.apps.kotlin.tracker.pojo.ListItem;

import java.util.ArrayList;
import java.util.List;

public class Data /*implements LoaderManager.LoaderCallbacks<List<ListItem>> */{

    public ArrayList<Item> items = new ArrayList<>();
    Context context;

    private static Data ourInstance = new Data();

    public static Data getInstance() {
        return ourInstance;
    }

    private Data() {}
    {
        items.add(new Item("ukraine", "Ukraine", "not set"));
        items.add(new Item("russia", "Russia", "not set"));
        items.add(new Item("sweden", "Sweden", "not set"));
        items.add(new Item("norway", "Norway", "not set"));
    }

    /*@Override
    public Loader<List<ListItem>> onCreateLoader(int id, Bundle args) {
        return new DataLoader(context, R.raw.europe);
    }

    @Override
    public void onLoadFinished(Loader<List<ListItem>> loader, List<ListItem> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<ListItem>> loader) {

    }*/
}
