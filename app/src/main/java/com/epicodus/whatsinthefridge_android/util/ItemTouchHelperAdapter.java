package com.epicodus.whatsinthefridge_android.util;


public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPostion);
    void onItemDismiss(int position);
}
