package net.vnnz.apps.kotlin.tracker;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import net.vnnz.apps.kotlin.tracker.pojo.ListItem;
import net.vnnz.apps.kotlin.tracker.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

public class DataLoader extends AsyncTaskLoader<List<ListItem>> {

    int resId;

    public DataLoader(Context context, int resId) {
        super(context);
        this.resId = resId;
        onContentChanged();
    }

    @Override
    public List<ListItem> loadInBackground() {
        String jsonString = ResourceUtils.Companion.readJSONfromRaw(resId, getContext());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<ListItem>();
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

}
