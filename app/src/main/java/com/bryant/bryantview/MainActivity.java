package com.bryant.bryantview;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.bryant.selectorlibrary.DSelectorPopup;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DSelectorPopup dSelectorPopup;
    ConstraintLayout constraintLayout;
    ArrayList<String> list = new ArrayList<>();
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i <= 10; i++) {
            list.add("YMF"+i);
        }
        dSelectorPopup = new DSelectorPopup(this,list);
        dSelectorPopup.setGradual_color(0xffD4D4D4).build();
/*        dSelectorPopup.setOffset(5)
                      .setTextSize(30)
                .setTextcolor_selection(getResources().getColor(R.color.colorAccent))
                .setTextcolor_unchecked(getResources().getColor(R.color.colorPrimary))
                .setGradual_color(0xffD81B60)
                .setTitleText("我是标题")
                .setTitleColor(getResources().getColor(R.color.colorPrimary))
                .setTitleSize(25)
                .setButton_background(getResources().getDrawable(R.drawable.popup_bg)).build();*/

        constraintLayout = findViewById(R.id.main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dSelectorPopup.popOutShadow(constraintLayout);
            }
        });

        /*点击监听器
        Tips:实现此监听器，点击button也会返回当前选中项的下标和文本，因此button既可以当做取消用，也可以当做确定用*/
        dSelectorPopup.setSelectorListener(new DSelectorPopup.SelectorClickListener() {
            @Override
            public void onSelectorClick(int position, String text) {
                Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
                dSelectorPopup.dismissPopup();
            }
        });

        //滑动监听器
        /*dSelectorPopup.setSelectoMoverListener(new DSelectorPopup.SelectorMoveListener() {
           @Override
           public void onSelectorMove(int position, String text) {
               Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
           }
       });*/
    }
}
