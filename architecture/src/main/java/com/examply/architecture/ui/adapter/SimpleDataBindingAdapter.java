package com.examply.architecture.ui.adapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/2/5 11:39
 */
public abstract class SimpleDataBindingAdapter<M, B extends ViewDataBinding> extends BaseDataBindingAdapter<M, B> {

    private final int layout;

    public SimpleDataBindingAdapter(int layout, @NonNull DiffUtil.ItemCallback<M> diffCallback) {
        super(diffCallback);
        this.layout = layout;
    }

    @Override
    protected @LayoutRes
    int getLayoutResId(int viewType) {
        return this.layout;
    }
}
