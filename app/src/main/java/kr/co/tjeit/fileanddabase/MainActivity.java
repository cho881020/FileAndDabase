package kr.co.tjeit.fileanddabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kr.co.tjeit.fileanddabase.util.TextFileManager;

public class MainActivity extends BaseActivity {

    private android.widget.Button loadBtn;
    private android.widget.Button saveBtn;
    private android.widget.Button deleteBtn;
    private android.widget.EditText contentEdt;

    TextFileManager mTextFileManager = new TextFileManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memoContent = mTextFileManager.load();
                contentEdt.setText(memoContent);

                Toast.makeText(mContext, "파일 불러오기 완료.", Toast.LENGTH_SHORT).show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextFileManager.saveMemo(contentEdt.getText().toString());

                contentEdt.setText("");

                Toast.makeText(mContext, "메모 저장 완료", Toast.LENGTH_SHORT).show();

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextFileManager.delete();
                contentEdt.setText("");

                Toast.makeText(mContext, "파일 삭제 완료.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.contentEdt = (EditText) findViewById(R.id.contentEdt);
        this.deleteBtn = (Button) findViewById(R.id.deleteBtn);
        this.saveBtn = (Button) findViewById(R.id.saveBtn);
        this.loadBtn = (Button) findViewById(R.id.loadBtn);

    }
}
