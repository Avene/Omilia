package com.avene.avene.omilia;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import com.avene.avene.omilia.model.Quiz;
import com.avene.avene.omilia.model.Section;

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
        realm.executeTransaction(_realm -> {
            _realm.where(Quiz.class).findAll().clear();

            try (
                    InputStream quizStream = getResources().openRawResource(R.raw.quizzes);
                    InputStream sectionStream = getResources().openRawResource(R.raw.sections);
//                    InputStream quizStream = getResources().openRawResource(R.raw.quizzes_sample);
//                    InputStream sectionStream = getResources().openRawResource(R.raw.sections_sample);
                    InputStream chapterStream = getResources().openRawResource(R.raw.chapters);
            ) {
                _realm.createOrUpdateAllFromJson(Quiz.class, quizStream);
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
