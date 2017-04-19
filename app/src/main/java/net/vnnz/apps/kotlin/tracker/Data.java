package net.vnnz.apps.kotlin.tracker;

/*
* Simple data Java class
* to check Kotlin -Java cross calling
* */

import net.vnnz.apps.kotlin.tracker.pojo.Item;

import java.util.ArrayList;

public class Data {

    ArrayList<Item> items = new ArrayList<>();

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
}
