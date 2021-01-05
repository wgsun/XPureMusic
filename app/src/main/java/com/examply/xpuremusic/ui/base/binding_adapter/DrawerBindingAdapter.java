package com.examply.xpuremusic.ui.base.binding_adapter;

import androidx.core.view.GravityCompat;
import androidx.databinding.BindingAdapter;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/1/5 11:05
 */
public class DrawerBindingAdapter {

    @BindingAdapter(value = {"isOpenDrawer"}, requireAll = false)
    public static void openDrawer(DrawerLayout drawerLayout, boolean isOpenDrawer) {
        if (isOpenDrawer && !drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(GravityCompat.START);
        } else {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @BindingAdapter(value = {"allowDrawOpen"}, requireAll = false)
    public static void allowDrawerOpen(DrawerLayout drawerLayout, boolean allowDrawerOpen) {
        drawerLayout.setDrawerLockMode(allowDrawerOpen
                ? DrawerLayout.LOCK_MODE_UNLOCKED
                : DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @BindingAdapter(value = {"bindDrawerListener"}, requireAll = false)
    public static void bindDrawerListener(DrawerLayout drawerLayout, DrawerLayout.DrawerListener listener){
        drawerLayout.addDrawerListener(listener);
    }





}
