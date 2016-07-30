package amsystemas.com.br.aplicationbd.data;

import android.app.Application;
import android.content.Context;

/**
 * Created by arthu on 30/07/2016.
 */
public class MyApplication  extends Application {

    public final static String tag = "MyApplication";
    public static volatile Context m_appContext = null;

    @Override
    public void onCreate() {
        super.onCreate();

        MyApplication.m_appContext = this.getApplicationContext();
    }

}
