package kr.co.tjeit.fileanddabase;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;


public class SharedPrefActivity extends BaseActivity {


    String prefName = "FileAndDatabasePref";
    SharedPreferences pref = mContext.getSharedPreferences(prefName, MODE_PRIVATE);

    private android.widget.CheckBox pushCheckBox;
    private android.widget.Switch autoLoginSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        pushCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pref.edit().putBoolean("pushCheck", isChecked).apply();
            }
        });
    }

    @Override
    public void setValues() {

        pushCheckBox.setChecked(pref.getBoolean("pushCheck", false));

    }

    @Override
    public void bindViews() {

        this.autoLoginSwitch = (Switch) findViewById(R.id.autoLoginSwitch);
        this.pushCheckBox = (CheckBox) findViewById(R.id.pushCheckBox);
    }
}
