package kr.co.tjeit.fileanddabase.util;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

//    파일의 입출력 경로를 불러오기 위한 도구
    Context mContext;

    public ObjectFileManager(Context context) {
        mContext = context;
    }

    public void save(HashMap<String, String> objData) {
//        파일입출력을 담당하는 FOS.
        FileOutputStream fos = null;
//        FOS를 통해 파일에 객체를 저장해주는 OOS.
        ObjectOutputStream oos = null;

        try {
//            실제로 저장될 파일을 열기.
            fos = mContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);

//            열린파일 (fos)을 통해 데이터를 전해주는 ObjectOutputStream 생성.
            oos = new ObjectOutputStream(fos);

//            oos를 통해 해쉬맵 데이터를 전달
            oos.writeObject(objData);

//            늦게 연것을 먼저 닫기.
            oos.close();
//            먼저 연것을 마지막에 닫음.
            fos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> load() {

        try {
            FileInputStream fis = mContext.openFileInput(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);

            HashMap<String, String> returnMemoData = null;
            returnMemoData = (HashMap<String, String>) ois.readObject();

            ois.close();
            fis.close();

            return returnMemoData;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void delete() {
        mContext.deleteFile(FILE_NAME);
    }

}
