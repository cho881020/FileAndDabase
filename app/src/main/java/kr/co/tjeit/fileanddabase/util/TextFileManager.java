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

//    메모장의 내용을 저장하는데 이용할 파일 명.
//    실제로, 내장 메모리 안에 memo.txt 파일이 생성되어 있음.
//    파일명은 짓고싶은대로 지을 수 있음.

    private final static String memoFileName = "memo.txt";

//    Context의 주요기능중 하나를 이용하고 있음.
//    주요기능? 앱마다 고유한 저장공간을 갖는데, 이 저장공간을 이용할 수 있게 해주는 역할.

    Context mContext;

    public TextFileManager(Context context) {
        mContext = context;
    }


    //        들어오는 스트링 객체를 -> 내장메모리의 파일로 저장.
    public void saveMemo(String inputData) {

//            메모의 내용이 들어오지 않으면 세이브를 취소.
        if (inputData == null || inputData.equals("")) {
            return;
        }

//        파일의 저장은, 앱의 입장에서는 산출물을 내보내는 작업.
//        그러므로 저장의 경우는 항상 OutputStream을 활용.
//        그 중에서 (OutputStream 중) 파일 저장이므로, FileOutputStream을 활용.
        FileOutputStream fosMemo = null;

        try {

//            안드로이드에서, FileOuputStream을 초기화 하는 방법.

//            Context의 기능중, 파일 출력 경로를 찾아오는 기능을 활용.
//            경로를 찾아온다? => 어느 폴더에 저장될것인가?
//            MODE_PRIVATE : ContextUtil에서도 활용.
//             => 외부 패키지에서 접근하는것을 막는다. (다른 앱)
//             ※ 루팅하면 접근 가능함.
            fosMemo = mContext.openFileOutput(memoFileName, Context.MODE_PRIVATE);

//            write : byte배열을 지정된 경로의 파일로 작성.
            fosMemo.write(inputData.getBytes());


//            한번 열어준 파일은 반드시 닫기 작업.
//            한 파일을 동시에 여러프로그램이 열 수 없음.
            fosMemo.close();

        } catch (FileNotFoundException e) {
//            해당 경로에 파일이 없을때 발생하는 예외
            e.printStackTrace();
        } catch (IOException e) {
//            파일에 내용을 저장하다가, 중간에 끊기거나 그 외의 사유로 인해 완료되지 못한경우.
            e.printStackTrace();
        }

    }

//    저장되어있는 파일을 불러오는 기능.
    public String load() {

        try {
//            Input : 불러오기의 경우는 앱의 입장에선 외부에 저장된 파일이 앱 내로 들어오는 과정
//            Context의 기능 : openFileInput 파일 읽는 경로 설정
            FileInputStream fisMemo = mContext.openFileInput(memoFileName);

//            파일입출력 : byte[]를 활용.
//            불러낸 파일을 저장하기 위한 byte배열을 생성.
//            배열은 만들때 크기를 지정할 필요. => fis가 가용한만큼으로 설정.
            byte[] memoData = new byte[fisMemo.available()];

            while (fisMemo.read(memoData) != -1) {
//                파일이 끝날때까지 계속해서 읽어들임.
//                read의 결과가 -1이라는건, 파일이 종료되었음을 이야기함.
//                EOF -> End Of File : 파일이 종료됨을 -1로 명시.

//                한번에 모든 파일 내용을 불러오지 못할 수 도 있음.
//                부분부분 읽게되는경우도 존재하므로
//                while문을 이용해 파일을 끝까지 읽도록 반복.

            }

//            파일에 접근이 끝났으니, 닫아줘서 다른 프로세스(프로그램) 접근 가능하도록.
            fisMemo.close();

//            저장된 바이트 배열을 이용하여 String변수를 생성. => return

            return new String(memoData);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public void delete() {
//        Context의 기능중 파일을 지워주는 기능 : deleteFile
        mContext.deleteFile(memoFileName);
    }

}
