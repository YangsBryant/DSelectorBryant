package com.bryant.selectorlibrary;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.ArrayList;

public class DSelectorPopup extends PopupWindow {

    private Activity activity;
    private Window window;
    private View view;
    private ArrayList<String> list;
    private SelectorClickListener selectorClickListener;
    private SelectorMoveListener selectoMoverListener;

    private int height = ViewGroup.LayoutParams.WRAP_CONTENT;//PopupWindow的高度
    private int offset = 1;// 对话框中当前项上面和下面的项数
    private int seletion = 0;// 默认选中项
    int textSize = 14; //字体大小
    int textcolor_selection;//选中文本颜色
    int textcolor_unchecked;//未选中文本颜色
    int gradual_color= 0xffEAEAEA;//分割线颜色
    boolean isFining = true;//是否开启分割线两端变细
    boolean isOutside = true;//点击弹窗外是否消失
    String titleText="请选择";//标题文字
    int titleSize=18;//标题文字大小
    int titleColor;//标题文字颜色
    String buttonText="取消";//按钮文字
    int buttonSize=16;//按钮文字大小
    int buttonColor;//按钮文字颜色
    Drawable drawable;//按钮背景
    int buttonWidt = ViewGroup.LayoutParams.WRAP_CONTENT;//按钮的宽度
    int buttonHeight;//按钮的高度
    int index;//当前滚动的下标
    String value;//当前滚动的文本
    public DSelectorPopup(Activity activity, ArrayList<String> list){
        this.activity = activity;
        this.list = list;
        titleColor = activity.getResources().getColor(R.color.titleColor);
        buttonColor = activity.getResources().getColor(R.color.white);
        drawable = activity.getResources().getDrawable(R.drawable.botton);
        textcolor_selection = activity.getResources().getColor(R.color.textcolor_selection);
        textcolor_unchecked = activity.getResources().getColor(R.color.textcolor_unchecked);
        buttonHeight = getPixelsFromDp(40);
    }

    private void initView(){
        LayoutInflater inflater = LayoutInflater.from(activity);
        window = activity.getWindow();
        view = inflater.inflate(R.layout.sb_popupp, null);
        setContentView(view);
        setHeight(height);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(isOutside);
        this.setOutsideTouchable(isOutside);
        this.setAnimationStyle(R.style.popup_style);
        index = seletion;
        value = list.get(seletion);

        SelectorView selectorView = view.findViewById(R.id.selectorView);
        selectorView.setOffset(offset);
        selectorView.setSeletion(seletion);
        selectorView.setTextSize(textSize);
        selectorView.setTextcolor_selection(textcolor_selection);
        selectorView.setTextcolor_unchecked(textcolor_unchecked);
        selectorView.setGradual_color(gradual_color);
        selectorView.setFining(isFining);
        selectorView.setItems(list);
        selectorView.setOnMoveViewListener(new SelectorView.OnMoveViewListener(){
            public void onSelected(int selectedIndex, String item) {
                index = selectedIndex;
                value = item;
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

        TextView textView = view.findViewById(R.id.textview);
        textView.setText(titleText);
        textView.setTextSize(titleSize);
        textView.setTextColor(titleColor);

        Button selectorButton = view.findViewById(R.id.selectorButton);
        selectorButton.setText(buttonText);
        selectorButton.setTextSize(buttonSize);
        selectorButton.setTextColor(buttonColor);
        selectorButton.setBackground(drawable);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) textView.getLayoutParams();
        linearParams.width = buttonWidt;
        linearParams.height = buttonHeight;
        selectorButton.setLayoutParams(linearParams);
        selectorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectorClickListener!=null) {
                    selectorClickListener.onSelectorClick(index, value);
                }
                dismissPopup();
            }
        });
    }

    //参数设置完毕，一定要build一下
    public void build(){
        initView();
    }

    //PopupWindow的高度
    public DSelectorPopup setHeights(int height) {
        if(height>0) {
            this.height = getPixelsFromDp(height);
        }
        return this;
    }

    // 点击弹窗外是否消失
    public DSelectorPopup isOutside(boolean bl) {
        this.isOutside = bl;
        return this;
    }

    // 对话框中当前项上面和下面的项数
    public DSelectorPopup setSeletion(int offset) {
        this.offset = offset;
        return this;
    }

    // 默认选中项
    public DSelectorPopup setOffset(int seletion) {
        this.seletion = seletion;
        return this;
    }

    //文本字体大小
    public DSelectorPopup setTextSize(int size) {
        this.textSize = size;
        return this;
    }

    //选中文本颜色
    public DSelectorPopup setTextcolor_selection(int textcolor_selection) {
        this.textcolor_selection = textcolor_selection;
        return this;
    }

    //未选中文本颜色
    public DSelectorPopup setTextcolor_unchecked(int textcolor_unchecked) {
        this.textcolor_unchecked = textcolor_unchecked;
        return this;
    }

    //分割线颜色
    public DSelectorPopup setGradual_color(int gradual_color) {
        this.gradual_color = gradual_color;
        return this;
    }

    //是否开启分割线两端变细
    public DSelectorPopup setFining(boolean fining) {
        isFining = fining;
        return this;
    }

    //标题的文字
    public DSelectorPopup setTitleText(String titleText) {
        this.titleText = titleText;
        return this;
    }

    //标题文字大小
    public DSelectorPopup setTitleSize(int titleSize) {
        this.titleSize = titleSize;
        return this;
    }

    //标题文字颜色
    public DSelectorPopup setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    //按钮文本
    public DSelectorPopup setButtonText(String buttonText) {
        this.buttonText = buttonText;
        return this;
    }

    //按钮文字大小
    public DSelectorPopup setButtonSize(int buttonSize) {
        this.buttonSize = buttonSize;
        return this;
    }

    //按钮文字颜色
    public DSelectorPopup setButtonColor(int buttonColor) {
        this.buttonColor = buttonColor;
        return this;
    }

    //按钮背景
    public DSelectorPopup setButton_background(Drawable drawable) {
        this.drawable = drawable;
        return this;
    }

    public DSelectorPopup setButtonWidt(int buttonWidt) {
        this.buttonWidt = getPixelsFromDp(buttonWidt);
        return this;
    }

    public DSelectorPopup setButtonHeight(int buttonHeight) {
        this.buttonHeight = getPixelsFromDp(buttonHeight);
        return this;
    }

    //显示PopupWindow
    public DSelectorPopup popOutShadow(View view) {
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
        return this;
    }

    //关闭PopupWindow
    public DSelectorPopup dismissPopup(){
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 1f;
        window.setAttributes(lp);
        this.dismiss();
        return this;
    }

    //px转dp
    private int getPixelsFromDp(int size){
        DisplayMetrics metrics =new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (size * metrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT;
    }

    //点击监听器
    public interface SelectorClickListener {
        void onSelectorClick(int position,String text);
    }

    public void setSelectorListener(SelectorClickListener selectorListener) {
        this.selectorClickListener = selectorListener;
    }

    //滑动监听器
    public interface SelectorMoveListener {
        void onSelectorMove(int position,String text);
    }

    public void setSelectoMoverListener(SelectorMoveListener selectoMoverListener) {
        this.selectoMoverListener = selectoMoverListener;
    }

}
