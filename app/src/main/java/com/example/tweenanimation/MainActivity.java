package com.example.tweenanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import static android.view.animation.Animation.RELATIVE_TO_SELF;
import static android.view.animation.Animation.REVERSE;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtonAction();
    }

    private void setButtonAction() {
        //平移动画(JAVA)
        final Button button1 = findViewById(R.id.button1);
        //创建平移动画的对象：平移动画对应的Animation子类为TranslateAnimation
        //参数分别是：
        // １.fromXDelta　视图在水平方向ｘ移动的起始值
        // 2.toXDelta 视图在水平方向ｘ移动的结束值
        // 3.fromYDelta 视图在竖直方向ｙ移动的起始值
        // 4.toYDelta 视图在竖直方向ｙ移动的结束值
        final Animation translateAnimation1 = new TranslateAnimation(0,500,0,500);
        //设置动画持续时间
        translateAnimation1.setDuration(3000);
        //动画结束之后还原控件起始状态, 默认为true
        translateAnimation1.setFillBefore(true);
        //动画结束之后保留在结束状态, 默认为flase
        translateAnimation1.setFillAfter(true);
        //setFillEnabled为true则取fillBefore的值,默认为flase
        translateAnimation1.setFillEnabled(true);
        //设置动画重复次数
        translateAnimation1.setRepeatCount(1);
        //动画重复播放时的方式：RESTART, REVERSE
        translateAnimation1.setRepeatMode(REVERSE);
        //调用start之后，等待动画开始运行的时间，单位为毫秒
        //animation.setStartOffset(1000);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始动画
                button1.startAnimation(translateAnimation1);
                //设置动画状态监听
                translateAnimation1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.d(TAG, "平移动画 animationStart");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.d(TAG, "平移动画 animationEnd");
                        /*Animation animation1 = new TranslateAnimation(500,0,500,0);
                        animation1.setDuration(3000);
                        button1.startAnimation(animation1);*/
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.d(TAG, "平移动画　onAnimationReapeat");
                    }
                });
            }
        });


        //平移动画(XML)
        final Button button2 = findViewById(R.id.button2);
        //创建动画对象并传入设置的动画效果xml文件
        final Animation translateAnimation2 = AnimationUtils.loadAnimation(this, R.anim.translate_animation);
        //设置动画的增值器：动画结束时弹起
        Interpolator interpolator = new BounceInterpolator();
        translateAnimation2.setInterpolator(interpolator);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2.startAnimation(translateAnimation2);
            }
        });


        //缩放动画(JAVA)
        final Button button3 = findViewById(R.id.button3);
        // 创建缩放动画的对象 & 设置动画效果：缩放动画对应的Animation子类为ScaleAnimation
        // 参数说明:
        // 1. fromX ：动画在水平方向X的起始缩放倍数
        // 2. toX ：动画在水平方向X的结束缩放倍数
        // 3. fromY ：动画开始前在竖直方向Y的起始缩放倍数
        // 4. toY：动画在竖直方向Y的结束缩放倍数
        // 5. pivotXType:缩放轴点的x坐标的模式
        // 6. pivotXValue:缩放轴点x坐标的相对值
        // 7. pivotYType:缩放轴点的y坐标的模式
        // 8. pivotYValue:缩放轴点y坐标的相对值

        // pivotXType = Animation.ABSOLUTE:缩放轴点的x坐标 =  View左上角的原点 在x方向 加上 pivotXValue数值的点(y方向同理)
        // pivotXType = Animation.RELATIVE_TO_SELF:缩放轴点的x坐标 = View左上角的原点 在x方向 加上 自身宽度乘上pivotXValue数值的值(y方向同理)
        // pivotXType = Animation.RELATIVE_TO_PARENT:缩放轴点的x坐标 = View左上角的原点 在x方向 加上 父控件宽度乘上pivotXValue数值的值 (y方向同理)
        final Animation scaleAnimation1 = new ScaleAnimation(0,2,0,2,Animation.RELATIVE_TO_SELF,0.1f,Animation.RELATIVE_TO_SELF,0.5f);
        // 固定属性的设置都是在其属性前加“set”，如setDuration（）
        scaleAnimation1.setDuration(3000);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button3.startAnimation(scaleAnimation1);
            }
        });


        //缩放动画(XML)
        final Button button4 = findViewById(R.id.button4);
        //创建动画对象并传入设置的动画效果xml文件
        final Animation scaleAnimation2 = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button4.startAnimation(scaleAnimation2);
            }
        });


        //透明动画(JAVA)
        final Button button5 = findViewById(R.id.button5);
        // 创建透明度动画的对象 & 设置动画效果：透明度动画对应的Animation子类为AlphaAnimation
        // 参数说明:
        // 1. fromAlpha:动画开始时视图的透明度(取值范围: 0 ~ 1), 1不透明，０全透明
        // 2. toAlpha:动画结束时视图的透明度(取值范围: 0 ~ 1)
        final Animation alphaAnimation1 = new AlphaAnimation(1, 0);
        alphaAnimation1.setDuration(3000);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button5.startAnimation(alphaAnimation1);
            }
        });


        //透明动画(XML)
        final Button button6 = findViewById(R.id.button6);
        final Animation alphaAnimation2 = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button6.startAnimation(alphaAnimation2);
            }
        });


        //旋转动画(JAVA)
        final Button button7 = findViewById(R.id.button7);
        // 创建旋转动画的对象 & 设置动画效果：旋转动画对应的Animation子类为RotateAnimation
        // 参数说明:
        // 1. fromDegrees ：动画开始时 视图的旋转角度(正数 = 顺时针，负数 = 逆时针)
        // 2. toDegrees ：动画结束时 视图的旋转角度(正数 = 顺时针，负数 = 逆时针)
        // 3. pivotXType：旋转轴点的x坐标的模式
        // 4. pivotXValue：旋转轴点x坐标的相对值
        // 5. pivotYType：旋转轴点的y坐标的模式
        // 6. pivotYValue：旋转轴点y坐标的相对值

        // pivotXType = Animation.ABSOLUTE:旋转轴点的x坐标 =  View左上角的原点 在x方向 加上 pivotXValue数值的点(y方向同理)
        // pivotXType = Animation.RELATIVE_TO_SELF:旋转轴点的x坐标 = View左上角的原点 在x方向 加上 自身宽度乘上pivotXValue数值的值(y方向同理)
        // pivotXType = Animation.RELATIVE_TO_PARENT:旋转轴点的x坐标 = View左上角的原点 在x方向 加上 父控件宽度乘上pivotXValue数值的值 (y方向同理)
        final Animation rotateAnimation1 = new RotateAnimation(0,270,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation1.setDuration(3000);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button7.startAnimation(rotateAnimation1);
            }
        });

        //旋转动画(XML)
        final Button button8 = findViewById(R.id.button8);
        final Animation rotateAnimation2 = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button8.startAnimation(rotateAnimation2);
            }
        });


        //组合动画(JAVA)
        final Button button9 = findViewById(R.id.button9);
        //创建组合动画对象，设置为ture,则所有的子动画共用interpolator
        final AnimationSet animationSet1 = new AnimationSet(true);
        // 设置组合动画的属性
        // 逐个创建子动画(方式同单个动画创建方式,此处不作过多描述)
        // 子动画1:旋转动画
        Animation rotate = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(1000);
        rotate.setRepeatMode(Animation.RESTART);
        rotate.setRepeatCount(Animation.INFINITE);

        // 子动画2:平移动画
        Animation translate = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT,-0.5f,
                TranslateAnimation.RELATIVE_TO_PARENT,0.5f,
                TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        translate.setDuration(10000);

        // 子动画3:透明度动画
        Animation alpha = new AlphaAnimation(1,0.4f);
        alpha.setDuration(3000);
        alpha.setStartOffset(7000);

        // 子动画4:缩放动画
        Animation scale1 = new ScaleAnimation(1,0.5f,1,0.5f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scale1.setDuration(1000);
        scale1.setStartOffset(4000);

        // 步骤4:将创建的子动画添加到组合动画里
        animationSet1.addAnimation(alpha);
        animationSet1.addAnimation(rotate);
        animationSet1.addAnimation(translate);
        animationSet1.addAnimation(scale1);

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button9.startAnimation(animationSet1);
            }
        });

        //组合动画(XML)
        final Button button10 = findViewById(R.id.button10);
        final Animation animationSet2 = AnimationUtils.loadAnimation(this , R.anim.set_animation);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button10.startAnimation(animationSet2);
            }
        });
    }
}
