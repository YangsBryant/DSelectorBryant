package com.bryant.selectorlibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import java.util.ArrayList;

public class SelectorPopup extends PopupWindow implements View.OnClickListener {

    private Activity activity;
    private Window window;
    private SelectorClickListener selectorClickListener;
    private SelectorMoveListener selectoMoverListener;

    public SelectorPopup(Activity activity,ArrayList<String> list){
        this.activity = activity;
        LayoutInflater inflater = LayoutInflater.from(activity);
        window = activity.getWindow();
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.sb_popupp, null);
        setContentView(view);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.popup_style);

        SelectorView selectorView = view.findViewById(R.id.selectorView);
        selectorView.setOffset(1);// 对话框中当前项上面和下面的项数
        selectorView.setItems(list);// 设置数据源
        selectorView.setSeletion(1);// 默认选中第三项
        selectorView.setOnWheelViewListener(new SelectorView.OnWheelViewListener(){
            public void onSelected(int selectedIndex, String item) {
                if (selectoMoverListener != null) {
                    selectoMoverListener.onSelectorMove(selectedIndex, item);
                }
            }
        });
        selectorView.setOnClickViewListener(new SelectorView.OnClickViewListener() {
            @Override
            public void onClickView(int position, String text) {
                if(selectorClickListener!=null)
                    selectorClickListener.onSelectorClick(position,text);
            }
        });
        Button selectorButton = view.findViewById(R.id.selectorButton);
        selectorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissPopup();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    public void popOutShadow(View view) {
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.5f;
        window.setAttributes(lp);
        this.showAtLocation(view, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp1 = window.getAttributes();
                lp1.alpha = 1f;
                window.setAttributes(lp1);
            }
        });
    }

    public void dismissPopup(){
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 1f;
        window.setAttributes(lp);
        this.dismiss();
    }

    private int getPixelsFromDp(int size){
        DisplayMetrics metrics =new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return(size * metrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT;
    }

    public interface SelectorClickListener {
        void onSelectorClick(int position,String text);
    }

    public void setSelectorListener(SelectorClickListener selectorListener) {
        this.selectorClickListener = selectorListener;
    }
    public interface SelectorMoveListener {
        void onSelectorMove(int position,String text);
    }

    public void setSelectoMoverListener(SelectorMoveListener selectoMoverListener) {
        this.selectoMoverListener = selectoMoverListener;
    }

}
