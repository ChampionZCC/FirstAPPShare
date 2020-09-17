package com.example.myapplication.fragmnet;

import com.example.myapplication.R;

import java.lang.reflect.Method;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author zhoucong
 */
public class FragmentManagerHandler {

    public static void addAsRootFragment(FragmentManager fragmentManager, Fragment fragment,int resid) {
        if (getFragmentsSize(fragmentManager) != 0) {
            //移除所有fragment
            for (Fragment f : fragmentManager.getFragments()) {
                fragmentManager
                        .beginTransaction()
                        .remove(f)
                        .commitAllowingStateLoss();
            }
        }
        fragmentManager
                .beginTransaction()
                .replace(resid, fragment)
                .commitAllowingStateLoss();
    }

    public static void pushFragment(FragmentManager fragmentManager, Fragment previousFragment, Fragment fragment) {
        if (previousFragment != null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.content, fragment)
                    .show(fragment)
                    .hide(previousFragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        } else {
            //第一个使用addAsRootFragment加载
            addAsRootFragment(fragmentManager, fragment,R.id.content);
        }
    }

    public static void popFragment(FragmentManager fragmentManager) {
        noteStateNotSaved(fragmentManager);
        fragmentManager.popBackStackImmediate();
    }

    public static void showFragment(FragmentManager fragmentManager, Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .show(fragment)
                .commitAllowingStateLoss();
    }

    public static void hiddenFragment(FragmentManager fragmentManager, Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .hide(fragment)
                .commitAllowingStateLoss();
    }

    private static int getFragmentsSize(FragmentManager fragmentManager) {
        return fragmentManager.getFragments().size();
    }

    /**
     * 用于修复 Caused by: java.lang.IllegalStateException:
     * Can not perform this action after onSaveInstanceStat的bug
     */
    private static void noteStateNotSaved(FragmentManager fragmentManager) {
        try {
            Class<? extends FragmentManager> aClass = fragmentManager.getClass();
            Method method = aClass.getMethod("noteStateNotSaved");
            method.setAccessible(true);
            method.invoke(fragmentManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
