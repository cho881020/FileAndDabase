package kr.co.tjeit.fileanddabase.util;

import android.content.Context;

import java.io.FileInputStream;
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

    public String load() {

        try {
            FileInputStream fisMemo = mContext.openFileInput(memoFileName);

            byte[] memoData = new byte[fisMemo.available()];

            while (fisMemo.read(memoData) != -1) {
//                파일이 끝날때까지 계속해서 읽어들임.
//                read의 결과가 -1이라는건, 파일이 종료되었음을 이야기함.
            }

            fisMemo.close();

            return new String(memoData);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public void delete() {
        mContext.deleteFile(memoFileName);
    }

}
