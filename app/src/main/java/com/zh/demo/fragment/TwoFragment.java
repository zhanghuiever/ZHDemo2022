package com.zh.demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zh.demo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TwoFragment extends Fragment {
    public static final String TAG = "Fragment_LifeCycle";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "twofragment_param1";
    private static final String ARG_PARAM2 = "twofragment_param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TwoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TwoFragment newInstance(String param1, String param2) {
        TwoFragment fragment = new TwoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "TwoFragment: onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "TwoFragment: onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "TwoFragment: onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "TwoFragment: onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "TwoFragment: onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "TwoFragment: onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "TwoFragment: onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "TwoFragment: onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "TwoFragment: onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "TwoFragment: onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "TwoFragment: onDetach");
    }
}