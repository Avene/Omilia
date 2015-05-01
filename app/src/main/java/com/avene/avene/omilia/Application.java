package com.avene.avene.omilia;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by yamai on 4/29/2015.
 */
public class Application extends android.app.Application {
    public static final String DEFAULT_SENTENCES_FILE_NAME = "sentences.json";
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        File file = getDefaultSentencesFile();
//        if ( file.exists() == false ) { TODO Allow choosing restore default sentences or not
        Resources res = this.getResources();
        try {
            fileCopy(res.openRawResource(R.raw.sentences), openFileOutput(file.getName(), MODE_PRIVATE));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void fileCopy(InputStream is, OutputStream os) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        ) {
            String str;
            while ((str = br.readLine()) != null) {
                bw.append(str + "\n");
            }
            bw.flush();
        }
    }

    public static Context getAppContext() {
        return sContext;
    }

    public static File getDefaultSentencesFile() {
        return getAppContext().getFileStreamPath(DEFAULT_SENTENCES_FILE_NAME);
    }
}
