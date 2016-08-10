package ru.yandex.yamblz.ui.fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.animation.ViewAnimationUtils;

public class ContentFragment extends BaseFragment {

    public static ContentFragment newInstance(int color) {

        Bundle args = new Bundle();
        args.putInt("color", color);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    Animator anim;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null && view.isAttachedToWindow()) {
            View child = view.findViewById(R.id.text);
            int color = getArguments().getInt("color");
            child.setBackgroundColor(color);
            int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
            int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
            anim = ViewAnimationUtils.createCircularReveal(child, width / 2, height, 0, height + 500);
            anim.setDuration(700);
            anim.setInterpolator(new AccelerateDecelerateInterpolator());
            anim.start();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (anim != null)
            anim.cancel();
    }
}
