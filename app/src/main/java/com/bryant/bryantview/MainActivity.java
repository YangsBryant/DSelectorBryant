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
        dSelectorPopup.build();
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
        dSelectorPopup.setSelectorListener(new DSelectorPopup.SelectorClickListener() {
            @Override
            public void onSelectorClick(int position, String text) {
                Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
                dSelectorPopup.dismiss();
            }
        });
        /*dSelectorPopup.setSelectoMoverListener(new DSelectorPopup.SelectorMoveListener() {
           @Override
           public void onSelectorMove(int position, String text) {
               Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
           }
       });*/
    }
}
