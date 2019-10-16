package com.example.logindemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG = "MainActivity";
    private EditText et_username;
    private EditText et_password;
    private Button login;
    private Button exit;
    private int count = 5;
    private Button transit;
    private CheckBox cb_remember;
    private boolean flag = false;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        et_username = (EditText) this.findViewById(R.id.et_username);
        et_password = (EditText) this.findViewById(R.id.et_password);
        login = (Button) this.findViewById(R.id.btn_login);
        exit = (Button) this.findViewById(R.id.btn_exit);
//        transit = (Button) this.findViewById(R.id.transit);
        cb_remember = (CheckBox) this.findViewById(R.id.checkbox);


        login.setOnClickListener(this);
        exit.setOnClickListener(this);


        StatusBarUtils.setTransparent(this);


    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_login:

                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                //默认登陆帐号 taotaobujue  123456
                if (username.equals("taotaobujue")&& password.equals("123456")){
                    if (!flag){
                        Toast.makeText(this,"登陆成功",Toast.LENGTH_LONG).show();
                        count = 5;
                    }else{
                        Toast.makeText(this,"Login Successful！",Toast.LENGTH_LONG).show();
                    }
                }else {
                    if (!flag){
                        if (count <1){
                            login.setEnabled(false);
                        }
                        Toast.makeText(this,"帐号或密码错误,还可输入"+count+"次",Toast.LENGTH_LONG).show();
                        count--;

                    }else{
                        Toast.makeText(this,"Login Fail！",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.btn_exit:
               System.out.println("按了结束按钮");
               System.exit(0);
               break;

            default:
                break;
        }
    }


    /**
     * 更改应用语言
     *
     * @param locale
     */
    public void changeAppLanguage(Locale locale) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        getResources().updateConfiguration(configuration, metrics);
        //重新启动Activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}

