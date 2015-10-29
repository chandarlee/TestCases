package com.netease.lcd.lcdtestcases.cases.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.netease.lcd.lcdtestcases.R;

/**
 * 目的：测试从Fragment、子Fragment中调用startActivityForResult，在目标Activity中setResult后，结果是否能够传递到对应的调用Fragment中
 *
 * 结论：
 * 1.在目标Activity中调用setResult方法返回结果后，只有在目标Activity Finish，调用Activity Resume之后，才会触发onActivityResult的回调方法接受到返回数据
 * 2.从父Fragment中startActivityForResult，onActivityResult会正确回调
 * 3.从子Fragment中startActivityForResult，onActivityResult不能正确回调，返回结果最多只能传递到父亲Fragment，不能定位到子Fragment
 * 原因：requestcode只有低16位才有效，高16位值为(fragment.mIndex + 1)，mIndex用于定位到具体的fragment，用于返回结果时分发
 * 但子Fragment的mIndex值由其父亲Fragment的ChildFragmentManager管理，父亲Fragment的FragmentManger是对应的Activity中的FragmentManger，两者是不同的实例
 * 当结果分发到Activity层时，Activity使用mIndex值从FragmentManger中找到对应的Fragment(这里肯定找不到子Fragment，因为必须从ChildFragmentManager中才能找到)
 * 当结果传到父亲Fragment时，也没有继续分发
 */
public class FragmentRequestCodeTest extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_request_code_test);
    }

}
