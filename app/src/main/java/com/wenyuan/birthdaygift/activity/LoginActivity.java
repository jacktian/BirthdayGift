package com.wenyuan.birthdaygift.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wenyuan.birthdaygift.AppVar;
import com.wenyuan.birthdaygift.MyService;
import com.wenyuan.birthdaygift.R;
import com.wenyuan.birthdaygift.testheart.HeartView;

public class LoginActivity extends AppCompatActivity {

    EditText mEditText;
    private HeartView heartView;
    LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppVar.getInstance().setActivity("1");
        mEditText = (EditText) findViewById(R.id.user_password);
        heartView = (HeartView) findViewById(R.id.surfaceView);
        mLinearLayout = (LinearLayout) findViewById(R.id.linearlayout_login);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        mLinearLayout.setAnimation(animation);
        doStart();
    }

    /**
     * @param view
     */
    public void checkPassword(View view) {
        String pass = mEditText.getText().toString().trim();
        if (null != pass && !"".equals(pass)) {
            Intent intent = new Intent();
            if ("1234".equals(pass)) {
                intent.setClass(this, MainActivity.class);
            } else if ("123".equals(pass)) {
                intent.setClass(this, MainActivity.class);
            } else {
                new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setTitle("可惜不是你")
                        .setMessage("这是我为我女神私人定制的的")
                        .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(LoginActivity.this, "拜拜！", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .create().show();
                return;
            }
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "输入啊！光看这干嘛！", Toast.LENGTH_SHORT).show();
        }

    }

    //@Override
    //public boolean onTouchEvent(MotionEvent event) {
    //    heartView.reDraw();
    //    return super.onTouchEvent(event);
    //}
    //
    //public void reDraw(View v) {
    //
    //    heartView.reDraw();
    //
    //}

    /**
     * 开启服务
     */
    public void doStart() {
        startService(new Intent(this, MyService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String flag = AppVar.getInstance().getActivity();
        if ("1".equals(flag))
            stopService(new Intent(this, MyService.class));
    }
}
