package com.avene.avene.omilia;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import com.avene.avene.omilia.model.Section;
import com.avene.avene.omilia.model.Sentence;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;
import io.realm.exceptions.RealmMigrationNeededException;

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
        realm.executeTransaction(_realm -> {
            _realm.where(Sentence.class).findAll().clear();

            try (
                    InputStream sentenceStream = getResources().openRawResource(R.raw.sentences);
                    InputStream sectionStream = getResources().openRawResource(R.raw.sections);
                    InputStream chapterStream = getResources().openRawResource(R.raw.chapters);
            ) {
                _realm.createOrUpdateAllFromJson(Sentence.class, sentenceStream);
                _realm.createOrUpdateAllFromJson(Section.class, sectionStream);
//            _realm.createOrUpdateAllFromJson(Chapter.class, chapterStream);

            } catch (IOException e) {
                e.printStackTrace();
                _realm.cancelTransaction();
            }
        });
    }

    public static Context getCtx() {
        return sContext;
    }

}
