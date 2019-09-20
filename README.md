# DSelectorBryant
单选滚动选择器

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
                dSelectorPopup.popOutShadow(constraintLayout);
            }
        });

        /*点击监听器
        Tips:实现此监听器，点击button也会返回当前选中项的下标和文本，因此button既可以当做取消用，也可以当做确定用*/
        dSelectorPopup.setSelectorListener(new DSelectorPopup.SelectorClickListener() {
            @Override
            public void onSelectorClick(int position, String text) {
                Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
                dSelectorPopup.dismiss();
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
setHeights(int mHeight) | 显示具体的高度(dp),设置0是自适应(高度没有默认值，需要主动设置)
setHeights(int mHeight) | 显示具体的高度(dp),设置0是自适应(高度没有默认值，需要主动设置)
setHeights(int mHeight) | 显示具体的高度(dp),设置0是自适应(高度没有默认值，需要主动设置)
setHeights(int mHeight) | 显示具体的高度(dp),设置0是自适应(高度没有默认值，需要主动设置)
setHeights(int mHeight) | 显示具体的高度(dp),设置0是自适应(高度没有默认值，需要主动设置)
setHeights(int mHeight) | 显示具体的高度(dp),设置0是自适应(高度没有默认值，需要主动设置)
setHeights(int mHeight) | 显示具体的高度(dp),设置0是自适应(高度没有默认值，需要主动设置)
setHeights(int mHeight) | 显示具体的高度(dp),设置0是自适应(高度没有默认值，需要主动设置)
setHeights(int mHeight) | 显示具体的高度(dp),设置0是自适应(高度没有默认值，需要主动设置)
setHeights(int mHeight) | 显示具体的高度(dp),设置0是自适应(高度没有默认值，需要主动设置)
setHeights(int mHeight) | 显示具体的高度(dp),设置0是自适应(高度没有默认值，需要主动设置)
setHeights(int mHeight) | 显示具体的高度(dp),设置0是自适应(高度没有默认值，需要主动设置)
setHeights(int mHeight) | 显示具体的高度(dp),设置0是自适应(高度没有默认值，需要主动设置)
setHeights(int mHeight) | 显示具体的高度(dp),设置0是自适应(高度没有默认值，需要主动设置)
setHeights(int mHeight) | 显示具体的高度(dp),设置0是自适应(高度没有默认值，需要主动设置)
