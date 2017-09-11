package kr.co.tjeit.fileanddabase.util;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Created by user on 2017-09-11.
 */

public class ObjectFileManager {
//    저장하는 파일의 확장자는 마음대로 적어도 됨.
//    읽고 / 쓰는데 문제 없도록 가공할 필요가 있음.
//    Ex) .csv로 저장한다면 항목마다 사이사이에 쉼표를 끼워넣는다.
//    다른 프로그램이 활용하지 않는 확장자를 독자적으로 사용한다면, 굳이 가공할 필요 없다.
    private static final String FILE_NAME = "memo.obj";
    Context mContext;

    public ObjectFileManager(Context context) {
        mContext = context;
    }

    public void save(HashMap<String, String> objData) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = mContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);

            oos = new ObjectOutputStream(fos);
            oos.writeObject(objData);

            oos.close();
            fos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
