package com.example.plpla.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.plpla.R;
import com.example.plpla.viewgroup.ExpansionViewGroupManager;


public class ExpansionsViewGroupConstraintLayout extends ConstraintLayout {

    private final com.example.plpla.viewgroup.ExpansionViewGroupManager expansionViewGroupManager = new com.example.plpla.viewgroup.ExpansionViewGroupManager(this);

    public ExpansionsViewGroupConstraintLayout(Context context) {
        super(context);
        init(context, null);
    }

    public ExpansionsViewGroupConstraintLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ExpansionsViewGroupConstraintLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ExpansionsViewGroupConstraintLayout);
            if (a != null) {
                expansionViewGroupManager.setOpenOnlyOne(a.getBoolean(R.styleable.ExpansionsViewGroupConstraintLayout_expansion_openOnlyOne, false));
                a.recycle();
            }
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        expansionViewGroupManager.onViewAdded();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        expansionViewGroupManager.onViewAdded();
    }

}
