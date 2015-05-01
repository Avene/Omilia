package com.avene.avene.omilia;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import com.avene.avene.omilia.model.Sentence;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;

/**
 * Created by yamai on 4/29/2015.
 */
public class App extends android.app.Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

        loadDefaultSentences();

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void loadDefaultSentences() {
        Realm realm = Realm.getInstance(App.getCtx());
        realm.beginTransaction();
        realm.where(Sentence.class).findAll().clear();

        try(InputStream is = getResources().openRawResource(R.raw.sentences)) {
            realm.createOrUpdateAllFromJson(Sentence.class, is);
            realm.commitTransaction();

        } catch (IOException e) {
            e.printStackTrace();
            realm.cancelTransaction();
        }
    }

    public static Context getCtx() {
        return sContext;
    }

}
