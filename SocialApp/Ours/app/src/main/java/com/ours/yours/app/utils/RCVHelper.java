package com.ours.yours.app.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RCVHelper {
    private static BitmapHelper INSTANCE = new BitmapHelper();

    public static BitmapHelper getInstance() {
        return INSTANCE;
    }

    @SuppressLint("ObsoleteSdkInt")
    public static boolean isAtTop(RecyclerView recyclerView) {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return isAtTopBeforeIceCream(recyclerView);
        } else {
            return !ViewCompat.canScrollVertically(recyclerView, -1);
        }
    }

    private static boolean isAtTopBeforeIceCream(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int pos = linearLayoutManager.findFirstVisibleItemPosition();
            if (linearLayoutManager.findViewByPosition(pos).getTop() == recyclerView.getPaddingTop() && pos == 0)
                return true;
        }
        return false;
    }

    @SuppressLint("ObsoleteSdkInt")
    public static boolean isAtBottom(RecyclerView recyclerView) {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return isAtBottomBeforeIceCream(recyclerView);
        } else {
            return !ViewCompat.canScrollVertically(recyclerView, 1);
        }
    }

    private static boolean isAtBottomBeforeIceCream(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int count = recyclerView.getAdapter().getItemCount();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int pos = linearLayoutManager.findLastVisibleItemPosition();
            int lastChildBottom = linearLayoutManager.findViewByPosition(pos).getBottom();
            if (lastChildBottom == recyclerView.getHeight() - recyclerView.getPaddingBottom() && pos == count - 1)
                return true;
        }
        return false;
    }
}
