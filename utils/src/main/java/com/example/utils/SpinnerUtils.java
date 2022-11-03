package com.example.utils;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public final class SpinnerUtils {

    private SpinnerUtils() {
    }


    public static final void setSpinner(Context context, Spinner spinner, List<String> dataSource, String currValue) {
        ArrayAdapter adapter = new ArrayAdapter<String>(context, android.R.layout.simple_gallery_item, dataSource);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (null == currValue) {
            adapter.notifyDataSetChanged();
            return;
        }
        for (int i = 0; i < dataSource.size(); i++) {
            if (currValue.equals(dataSource.get(i))) {
                spinner.setSelection(i,true);
                break;
            }
        }
        adapter.notifyDataSetChanged();
    }

    public static final void setSpinner(Context context, Spinner spinner, String[] dataSource, String currValue) {
        ArrayAdapter adapter = new ArrayAdapter<String>(context, android.R.layout.simple_gallery_item, dataSource);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (null == currValue) {
            adapter.notifyDataSetChanged();
            return;
        }
        for (int i = 0; i < dataSource.length; i++) {
            if (currValue.equals(dataSource[i])) {
                spinner.setSelection(i);
                break;
            }
        }
        adapter.notifyDataSetChanged();
    }

    public static final void setSpinner(Context context, Spinner spinner, Object[] arr, String currValue) {
        ArrayAdapter adapter = new ArrayAdapter<Object>(context, android.R.layout.simple_gallery_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (null == currValue) {
            adapter.notifyDataSetChanged();
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (currValue.equals(arr[i].toString())) {
                spinner.setSelection(i);
                break;
            }
        }
        adapter.notifyDataSetChanged();
    }


}
