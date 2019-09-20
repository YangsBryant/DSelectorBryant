package com.bryant.bryantview;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bryant.selectorlibrary.SelectorPopup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SelectorPopup selectorPopup;
    ConstraintLayout layout;
    ArrayList<String> list = new ArrayList<>();
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i <= 10; i++) {
            list.add("YMF"+i);
        }
        selectorPopup = new SelectorPopup(this,list);

        layout = findViewById(R.id.main);
        textView = findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectorPopup.popOutShadow(layout);
            }
        });
        selectorPopup.setSelectorListener(new SelectorPopup.SelectorClickListener() {
            @Override
            public void onSelectorClick(int position, String text) {
                Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
                selectorPopup.dismiss();
            }
        });
       selectorPopup.setSelectoMoverListener(new SelectorPopup.SelectorMoveListener() {
           @Override
           public void onSelectorMove(int position, String text) {
               Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
           }
       });
    }
}
