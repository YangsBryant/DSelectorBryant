# DSelectorBryant
单选滚动选择器

![这是一张图片](https://github.com/YangsBryant/DSelectorBryant/blob/master/gifhome_320x693_10s.gif)

## 引入module
```java
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://www.jitpack.io' }
    }
}
```
```java
implementation 'com.github.YangsBryant:DSelectorBryant:1.0.2'
```

## 主要代码

```java
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

        constraintLayout = findViewById(R.id.main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出窗体
                dSelectorPopup.popOutShadow(constraintLayout);
            }
        });

        /*点击监听器
        Tips:实现此监听器，点击button也会返回当前选中项的下标和文本，因此button既可以当做取消用，也可以当做确定用*/
        dSelectorPopup.setSelectorListener(new DSelectorPopup.SelectorClickListener() {
            @Override
            public void onSelectorClick(int position, String text) {
                Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
                //缩回窗体
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
```
## 参数实例

```java
dSelectorPopup.setOffset(5)
                .setTextSize(30)
                .setTextcolor_selection(getResources().getColor(R.color.colorAccent))
                .setTextcolor_unchecked(getResources().getColor(R.color.colorPrimary))
                .setGradual_color(0xffD81B60)
                .setTitleText("我是标题")
                .setTitleColor(getResources().getColor(R.color.colorPrimary))
                .setTitleSize(25)
                .setButton_background(getResources().getDrawable(R.drawable.popup_bg)).build();
```
Tips:分割线的颜色值为：0xff 加上16进制颜色值，例如：0xffD81B60

## DSelectorBryant属性大全
方法名 | 属性
--------- | -------------
build() | 参数设置完毕，在最后build一下
setHeights(int height) | PopupWindow的高度，单位dp
isOutside(boolean bl) | 点击弹窗外是否消失，默认true
setSeletion(int offset) | 对话框中当前项上面和下面的项数
setOffset(int seletion) | 默认选中项
setTextSize(int size) | 文本字体大小
setTextcolor_selection(int textcolor_selection) | 选中文本颜色
setTextcolor_unchecked(int textcolor_unchecked) | 未选中文本颜色
setGradual_color(int gradual_color) | 分割线颜色
setFining(boolean fining) | 是否开启分割线两端变细，默认true
setTitleText(String titleText) | 标题的文字
setTitleSize(int titleSize) | 标题文字大小
setTitleColor(int titleColor) | 标题文字颜色
setButtonText(String buttonText) | 按钮文本
setButtonSize(int buttonSize) | 按钮文字大小
setButtonColor(int buttonColor) | 按钮文字颜色
setButton_background(Drawable drawable) | 按钮背景
setButtonWidt(int buttonWidt)| 按钮宽度，单位dp
setButtonHeight(int buttonHeight) | 按钮高度，单位dp
popOutShadow(View view) | 显示弹窗
dismissPopup() | 关闭弹窗
setSelectorListener(SelectorClickListener selectorListener) | 点击监听器
setSelectoMoverListener(SelectorMoveListener selectoMoverListener) | 滑动监听器

## 联系QQ：961606042
