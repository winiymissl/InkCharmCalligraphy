package com.example.module_community.ui;

/**
 * @Author winiymissl
 * @Date 2024-03-17 14:54
 * @Version 1.0
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import androidx.viewpager2.widget.ViewPager2;

/**
 * 一个用于嵌套滚动的宿主视图类，继承自FrameLayout。
 * 主要用于处理与ViewPager2的嵌套滚动逻辑。
 */
public class NestedScrollableHost extends FrameLayout {
    private int touchSlop; // 触摸滑动的最小距离
    private float initialX; // 触摸开始时的X坐标
    private float initialY; // 触摸开始时的Y坐标

    /**
     * 构造函数，初始化NestedScrollableHost。
     *
     * @param context 上下文对象。
     */
    public NestedScrollableHost(Context context) {
        super(context);
        init(context);
    }

    /**
     * 构造函数，带属性集。
     *
     * @param context 上下文对象。
     * @param attrs   属性集。
     */
    public NestedScrollableHost(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化函数，设置触摸滑动的最小距离。
     *
     * @param context 上下文对象。
     */
    private void init(Context context) {
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /**
     * 获取当前视图的父ViewPager对象。
     *
     * @return 父ViewPager对象，如果不存在则返回null。
     */
    private ViewPager2 getParentViewPager() {
        View v = getParentView();
        while (v != null && !(v instanceof ViewPager2)) {
            v = (View) v.getParent();
        }
        return (ViewPager2) v;
    }

    /**
     * 获取宿主视图的第一个子视图。
     *
     * @return 第一个子视图，如果没有则返回null。
     */
    private View getChildView() {
        return getChildCount() > 0 ? getChildAt(0) : null;
    }

    /**
     * 获取当前视图的父视图。
     *
     * @return 父视图对象。
     */
    private View getParentView() {
        return (View) getParent();
    }

    /**
     * 判断子视图是否能够沿着指定方向滚动。
     *
     * @param orientation 滚动方向，0代表水平，1代表垂直。
     * @param delta       滚动的偏移量。
     * @return 如果子视图能够滚动则返回true，否则返回false。
     */
    private boolean canChildScroll(int orientation, float delta) {
        int direction = (int) -Math.signum(delta); // 滚动方向
        switch (orientation) {
            case 0:
                return getChildView() != null && getChildView().canScrollHorizontally(direction);
            case 1:
                return getChildView() != null && getChildView().canScrollVertically(direction);
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * 处理触摸事件，决定是否拦截触摸事件。
     *
     * @param e 触摸事件。
     * @return 是否拦截触摸事件。
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        handleInterceptTouchEvent(e);
        return super.onInterceptTouchEvent(e);
    }

    /**
     * 根据触摸动作处理拦截逻辑。
     *
     * @param e 触摸事件。
     */
    private void handleInterceptTouchEvent(MotionEvent e) {
        ViewPager2 parentViewPager = getParentViewPager();
        if (parentViewPager == null) {
            return; // 如果没有找到父ViewPager，则不进行任何操作
        }

        int orientation = parentViewPager.getOrientation(); // 获取父ViewPager的 orientation

        // 如果向左或向右滑动时子视图都不能滚动，则不进行任何操作
        if (!canChildScroll(orientation, -1f) && !canChildScroll(orientation, 1f)) {
            return;
        }

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = e.getX();
                initialY = e.getY();
                getParent().requestDisallowInterceptTouchEvent(true); // 开始拦截触摸事件
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = e.getX() - initialX; // 水平方向移动距离
                float dy = e.getY() - initialY; // 垂直方向移动距离
                boolean isVpHorizontal = orientation == ViewPager2.ORIENTATION_HORIZONTAL; // ViewPager是否为水平方向

                float scaledDx = Math.abs(dx) * (isVpHorizontal ? .5f : 1f); // 根据ViewPager方向调整dx
                float scaledDy = Math.abs(dy) * (isVpHorizontal ? 1f : .5f); // 根据ViewPager方向调整dy

                // 根据滑动距离判断是否应该拦截事件
                if (scaledDx > touchSlop || scaledDy > touchSlop) {
                    if (isVpHorizontal == (scaledDy > scaledDx)) {
                        getParent().requestDisallowInterceptTouchEvent(false); // 拦截垂直滑动
                    } else {
                        if (canChildScroll(orientation, isVpHorizontal ? dx : dy)) {
                            getParent().requestDisallowInterceptTouchEvent(true); // 允许拦截
                        } else {
                            getParent().requestDisallowInterceptTouchEvent(false); // 不允许拦截
                        }
                    }
                }
                break;
        }
    }
}
