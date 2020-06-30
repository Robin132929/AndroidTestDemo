package com.robin.testdemo.fragmentlazyload;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robin.testdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentD#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentD extends Fragment {
    //判断是否已进行过加载，避免重复加载
    private boolean isLoad=false;
    //判断当前fragment是否可见
    private boolean isVisibleToUser = false;
    //判断当前fragment是否回调了resume
    private boolean isResume = false;
    private boolean isCallUserVisibleHint = false;
    private static final String TAG = "FragmentLifeCallback";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentD() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentD.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentD newInstance(String param1, String param2) {
        FragmentD fragment = new FragmentD();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_d, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        isResume=true;
        if (!isCallUserVisibleHint) isVisibleToUser=!isHidden();
        lazyLoad();
        if (!isLoad&&isHidden()){
            lazyLoad();
            isLoad=true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG, "setUserVisibleHint: "+isVisibleToUser+"  "+FragmentD.this);
        this.isVisibleToUser=isVisibleToUser;
        isCallUserVisibleHint=true;
        lazyLoad();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG, "onHiddenChanged: "+hidden+" "+FragmentD.this);
        isVisibleToUser=!hidden;
        lazyLoad();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoad=false;
        isCallUserVisibleHint=false;
        isVisibleToUser=false;
        isResume=false;
    }

    private void lazyLoad() {
        if (!isLoad&&isVisibleToUser&&isResume){
            //懒加载。。。
            isLoad=true;
        }
    }
}
