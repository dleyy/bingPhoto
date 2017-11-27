package com.dleyy.data.Exception;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by dleyy on 2017/11/24.
 */
public class ExceptionHandler {

    private Context context;

    private static ExceptionHandler handler;

    public static ExceptionHandler getInstance(Context context) {
        if (handler == null) {
            handler = new ExceptionHandler(context);
        }
        return handler;
    }

    public ExceptionHandler(Context context) {
        this.context = context;
    }

    public void handleException(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
