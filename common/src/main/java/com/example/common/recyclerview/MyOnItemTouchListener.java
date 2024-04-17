package com.example.common.recyclerview;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author winiymissl
 * @Date 2024-04-16 20:47
 * @Version 1.0
 */
public class MyOnItemTouchListener implements RecyclerView.OnItemTouchListener {
    private MyOnItemTouchListener.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onLongItemClick(View view, int position);

    }

    private RecyclerView rv;
    GestureDetector mGestureDetector;

    public MyOnItemTouchListener(Context context, RecyclerView rv, MyOnItemTouchListener.OnItemClickListener listener) {
        this.mListener = listener;
        this.rv = rv;
        mGestureDetector = new GestureDetector(context, new ItemTouchHelperGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        return mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View childView = rv.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null) {
                mListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
                return true;
            }
            return true;
        }

        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            View childView = rv.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null) {
                mListener.onLongItemClick(childView, rv.getChildAdapterPosition(childView));
            }
        }
    }
}