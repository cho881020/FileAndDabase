package kr.co.tjeit.fileanddabase.util;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by user on 2017-09-11.
 */

public class TextFileManager {

    private final static String memoFileName = "memo.txt";

    Context mContext;

    public TextFileManager(Context context) {
        mContext = context;
    }

    public void saveMemo(String inputData) {
//        들어오는 스트링 객체를 -> 내장메모리의 파일로 저장.

        if (inputData == null || inputData.equals("")) {
//            메모의 내용이 들어오지 않으면 세이브를 취소.
            return;
        }

        FileOutputStream fosMemo = null;

        try {
            fosMemo = mContext.openFileOutput(memoFileName, Context.MODE_PRIVATE);

            fosMemo.write(inputData.getBytes());

            fosMemo.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
