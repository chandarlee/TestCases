package com.netease.lcd.lcdtestcases.cases.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by hzlichengda on 2015/10/29.
 */
public class FragmentWithChild extends Fragment{

    private static final int REQUEST_CODE = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ChildFragment child = new ChildFragment();
        getChildFragmentManager().beginTransaction().add(getId(),child).commit();
    }

    /**
     * 当Fragment作为layout中的一个节点时，parent container必须拥有有效Id，或者fragment结点自己指定id或者tag，否则抛出异常
     * note：当Fragment作为layout中的一个节点时，onCreateView回调时container参数会为null
     * 如果Fragment结点指定了id，那么onCreateView返回的view，会自动设置其id值为结点中指定的值
     * 见：FragmentManager中的onCreateView()方法
     * 如果onCreateView返回的view没有指定tag值且结点中指定了tag值，则会自动设置其tag值为结点中指定的tag值
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = inflater.getContext();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        //linearLayout.setId(R.id.fragment_container);
        Button button = new Button(context);
        button.setText("Start Activity For Result from parent fragment!");
        button.setPadding(10, 10, 10, 10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //requestcode值只有低16位有效
                startActivityForResult(new Intent(getActivity(),FragmentAnimationTest.class),REQUEST_CODE);
            }
        });
        linearLayout.addView(button,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return linearLayout;
    }

    //作为父Fragment，能够正确接收到这个方法的回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("ParentFragment","request back " + requestCode);
        if (requestCode == REQUEST_CODE){
            Log.i("ParentFragment","request success");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    private static class ChildFragment extends Fragment{

        private static final int REQUEST_CODE = 2;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            //return super.onCreateView(inflater, container, savedInstanceState);
            Button button = new Button(container.getContext());
            button.setText("Start Activity For Result from child fragment!");
            button.setPadding(10, 10, 10, 10);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //requestcode值只有低16位有效
                    startActivityForResult(new Intent(getActivity(),FragmentAnimationTest.class),REQUEST_CODE);
                }
            });
            return button;
        }

        /**
         * 作为子Fragment，回调不会触发
         * @param requestCode
         * @param resultCode
         * @param data
         */
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            Log.i("ChildFragment","request back " + requestCode);
            if (requestCode == REQUEST_CODE){
                Log.i("ChildFragment","request success");
            }
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
