package kr.co.tjeit.fileanddabase.util;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by user on 2017-09-11.
 */

public class TxtManager {
    private static final String FILE_NAME = "memoFile01.txt";
    Context mContext;

    public TxtManager(Context context) {
        mContext = context;
    }

    public void save(String str) {

        try {
            FileOutputStream fos = mContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(str.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String load() {

        try {
            FileInputStream fis = mContext.openFileInput(FILE_NAME);
            byte[] contentBytes = new byte[fis.available()];
            while (fis.read(contentBytes) != -1) {}
            fis.close();
            return new String(contentBytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
//            try가 성공적이던, catch로 빠지던 무조건 실행줘야하는 기능.
//            finally는 어떤 경우에도 실행됨을 보장.
        }


        return "";
    }

    public void delete() {
        mContext.deleteFile(FILE_NAME);
    }

}
