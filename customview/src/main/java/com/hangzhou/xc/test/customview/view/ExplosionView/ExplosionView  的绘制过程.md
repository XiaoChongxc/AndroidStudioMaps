# ExplosionView  的绘制过程
> explosionView是一个粒子爆炸的view效果， 可以把view添加到explosionview中， 在点击该view的时候， view会变成粒子爆碎开， 掉到屏幕底部，动画结束后在恢复原样。

## 开发过程


#### 知识点：

1.快速创建一个属性动画
```
view.animate()          //给view创建一个属性动画
    .alpha(1f)          //设置透明度
    .translationX(10)   //X轴移动
    .scaleX(1.5)        //X轴缩放
    .setDuration(150)  //设置动画时间
    .start();          //启动动画

```
2.bitmap获取到指定位置的像素颜色

    bit.getPixel(x, y)
    
#### 概述
   主要分为两个部分：
   
1. ExplosionLayout是用来播放动画的一个壳子，主要任务是
- 初始化的时候创建一个覆盖全屏的view ，用来承载view的绘制
- 监听View的点击事件，在view被点击之后，执行动画效果 获取到view的位置和bitmap图像，根据传进来的不同类型，播放不同的动画效果
2. Explosion作为一个抽象类，主要是方便更换不同的效果，主要子类1.Particle，2.Shredder 在ExplosionLayout中改变type来切换不同的效果




##### 第一部分 ExplosionLayout 
- 初始化的时候创建一个覆盖全屏的view ，用来承载view的绘制

```
 private void attach2Activity(Activity activity) {
 ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
 ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
 ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.addView(this, lp);
    }
```
- 监听View的点击事件，在view被点击之后，执行动画效果

```

    /**
     * 给view 一个监听，当被点击的时候，执行动画效果
     *
     * @param view
     */
    public void addLinstener(final View view) {
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                explosion(view);
            }
        });
    }
```
```
  /***
     * 给 view 添加动画效果
     *
     * @param view
     */
    private void explosion(final View view) {
        //获取到传进来view 的位置信息
        Rect mRect = new Rect();
        view.getGlobalVisibleRect(mRect);
        //因为状态栏的高度问题， 这里要进行一个高度的偏移
        mRect.offset(0, -DensityUtils.dp2px(view.getContext(), 21));
        Bitmap bit = createBitmapFromView(view);
        Explosion explosion;
    if (type == TYPE_SHREDDER) {
        explosion = new Shredder();
    } else {
        explosion = new Particle();
    }

    final ValueAnimator explosionAnimation = ValueAnimator.ofObject(new ExplosionEvaluator(explosion, bit, mRect),
            new Shredder(0), new Shredder(1));
    explosionAnimation.setDuration(animation_time);
    //不能重复添加
    if (explostionSet.containsKey(view)) {
        return;
    }
    explostionSet.put(view, explosionAnimation);
    explosionAnimation.addListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationStart(Animator animation) {
            //动画开始的时候 需要隐藏原来的view
            view.animate().alpha(0).setDuration(150).start();
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            //动画结束   显示view  并且 移除动画
            view.animate().alpha(1f).setDuration(150).start();
            explostionSet.remove(view);
        }
    });
    explosionAnimation.start();
    startThread();
}

```
explostionSet 是一个属性动画的集合，用来保证每次点击都是独立的一个动画，依次播放集合里面的动画，最开始版本是在每个动画播放的时候都调用   postInvalidate()方法来刷新view，但这样频繁调用postInvalidate会消耗太多性能，这边就开启了一个线程，定时刷新view

```

    /***
     * 开启一个线程， 用来刷新当前view
     */
    private void startThread() {
        if (mThread == null) {
            isStop = false;
            mThread = new Thread() {
                @Override
                public void run() {
                    while (!isStop) {
                        if (explostionSet.size() != 0) {
                            try {
                                sleep(refresh_time);
                                postInvalidate();
                            } catch (Exception e) {
                                Log.d(TAG, "run: Exception" + e.toString());
                            }
                        } else {
                            isStop = true;
                            mThread = null;
                        }
                    }
                }
            };
            mThread.start();
        }
    }
```
在onDraw中 绘制view的变化，都是调用的动画，具体还是由 Explosion和他的子类实现具体的动画效果

```
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        it = explostionSet.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            ValueAnimator value = (ValueAnimator) entry.getValue();
            play(canvas, value);
        }
    }
```

```
  /***
     * 播放动画， 动态改变view 的状态
     *
     * @param canvas    画板
     * @param animation 动画
     */
    private void play(Canvas canvas, ValueAnimator animation) {
        if (!animation.isStarted()) { //动画结束时停止
            return;
        }
        Explosion explosion = (Explosion) animation.getAnimatedValue();
        explosion.draw(canvas, mPaint, explosion.getFactor());
        startThread();
    }
```



##### 第二部分 动画实现类 Explosion和他的子类

```

public abstract class Explosion {
    public Bitmap bit;
    public Rect mRect;
    private float factor;

    public float getFactor() {
        return factor;
    }

    public void setFactor(float factor) {
        this.factor = factor;
    }

    public Explosion(float factor) {
        this.factor = factor;
    }

    public Explosion() {
    }

    /***
     * 初始化
     *
     * @param bit
     */
    abstract void init(Bitmap bit, Rect mRect);

    /***
     * 画出效果
     *
     * @param canvas 画板
     * @param mPaint 画笔
     * @param factor 当前动画进度
     */
    abstract void draw(Canvas canvas, Paint mPaint, float factor);

}
```
Explosion是一个抽象类，需要子类去实现 初始化init()和 draw() 两个方法， 完成动画的绘制

- Explosion 子类 Particle 把view变成像素点，在init()初始化像素点的数组 在draw里面绘制。
```
public class Particle extends Explosion {

    public static final int DEFAULT_SIZE = 10;
    private final int offset = 30;


    Point[][] mPoints;

    public Particle() {

    }

    public Particle(float factor) {
        super(factor);
    }

    @Override
    void init(Bitmap bit, Rect mRect) {
        this.bit = bit;
        this.mRect = mRect;
        initParticle(bit);
    }

    @Override
    public void draw(Canvas canvas, Paint mPaint, float factor) {
        //画出像素点
        for (Point[] particles : mPoints) {
            for (Point p : particles) {
//                //根据动画 ， 对像素点做偏移缩小
                float progress = 1 - factor;
                advance(p, progress);
                mPaint.setColor(p.getColor());
//                mPaint.setAlpha((int) (progress * 255));//只是这样设置，透明色会显示为黑色
                int alpha = (int) (Color.alpha(p.getColor()) * progress);
                mPaint.setAlpha(alpha); //这样透明颜色就不是黑色了
                canvas.drawCircle(p.getX() + mRect.left, p.getY() + mRect.top, p.getSize(), mPaint);
            }
        }
    }


    /***
     * 初始化像素点
     */
    private void initParticle(Bitmap bit) {
        int radius = Particle.DEFAULT_SIZE;
        int num_x = bit.getWidth() / radius;
        int num_y = bit.getHeight() / radius;
        mPoints = new Point[num_x][num_y];
        for (int i = 0; i < num_x; i++) {
            for (int j = 0; j < num_y; j++) {
                mPoints[i][j] = new Point();
                int x = i * radius + mPoints[i][j].getSize() / 2;
                int y = j * radius + mPoints[i][j].getSize() / 2;
                mPoints[i][j].setColor(bit.getPixel(x, y));
                mPoints[i][j].setX(x);
                mPoints[i][j].setY(y);
                mPoints[i][j].setmRect(mRect);
            }
        }
    }

    /**
     * 根据进度， 调整 像素点的状态
     *
     * @param factor
     */
    private void advance(Point p, float factor) {
        //调整大小
        p.setSize((int) (DEFAULT_SIZE * factor));
        //左右随机偏移
        p.setX((int) (p.getX() + (0.5f - new Random().nextFloat()) * offset));
        //设置 Y
        p.setY((int) (mRect.bottom * (1 - factor) + p.getY()) + new Random().nextInt(mRect.height() / 2));
    }


    public class Point {
        /**
         * 在父控件的位置
         */
        private Rect mRect;
        /**
         * 像素点的大小
         */
        private int size = DEFAULT_SIZE;
        /**
         * x坐标
         */
        private int x;
        /**
         * Y坐标
         */
        private int y;
        /**
         * 像素点的颜色
         */
        private int color;

        public Rect getmRect() {
            return mRect;
        }

        public void setmRect(Rect mRect) {
            this.mRect = mRect;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

    }

}
```
- Explosion 子类 Shredder 把view变成纸片，在init()初始化像素点的数组 在draw里面绘制。

```

public class Shredder extends Explosion {
    private final int DEFAULT_HEIGHT = 20;
    private final int slice_height = DEFAULT_HEIGHT;
    Slice[] slices;
    int speed = 10;

    public Shredder() {

    }

    public Shredder(float factor) {
        super(factor);
    }

    @Override
    void init(Bitmap bit, Rect mRect) {
        this.mRect = mRect;
        this.bit = bit;
        initSlices(bit);
    }

    @Override
    public void draw(Canvas canvas, Paint mPaint, float factor) {
        mPaint.setAlpha(255);

//        for (int i = 0; i < slices.length; i++) {
//            Slice slice = slices[i];
//            canvas.drawBitmap(slice.getBitmap(), rect.left + slice.getX(), rect.top + slice.getY() + factor * speed * (i+1) * slice_height, mPaint);
//        }
        /**
         * 依次 下落， 自由落体式， 加速度
         */
        for (int i = 0; i < slices.length; i++) {
            Slice slice = slices[i];
            int y = (int) (factor * speed * slice_height * (i + 1)) - (slices.length - i - 1) * slice_height;
            if (y <= 0) y = 0;
            canvas.drawBitmap(slice.getBitmap(), mRect.left + slice.getX(), mRect.top + slice.getY() + y, mPaint);
        }
    }

    private void initSlices(Bitmap bit) {
        int size = (int) Math.ceil(bit.getHeight() * 1f / slice_height);
        slices = new Slice[size];
        for (int i = 0; i < size; i++) {
            slices[i] = new Slice();
            int y = i * slice_height;
            if (y > bit.getHeight() - slice_height) y = bit.getHeight() - slice_height;
            slices[i].setBitmap(Bitmap.createBitmap(bit, 0, y, bit.getWidth(), slice_height));
            slices[i].setX(0);
            slices[i].setY(y);
        }
    }


    public class Slice {
        private Bitmap bitmap;
        private int x;
        private int y;

        public Bitmap getBitmap() {
            return bitmap;
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
```

效果图 ：

源码地址：















    
    



    


    
    
  