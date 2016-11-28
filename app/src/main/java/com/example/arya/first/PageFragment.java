package com.example.arya.first;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PageFragment extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";

    public PageFragment() {

    }

    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_layout, container, false);
        TextView text = (TextView)rootView.findViewById(R.id.page_number_label);
//        View fragment = rootView.findViewById(R.id.fragment_history);

        Fragment history = getFragmentManager().findFragmentById(R.id.fg_history);
        List<Fragment> frs = getFragmentManager().getFragments();
        for (int i = 0; i < frs.size(); i++) {
            frs.get(i);
        }
//        ((Button)history.getView().findViewById(R.id.btn_frg_history)).setText("button1");

        Fragment myItems = getFragmentManager().findFragmentById(R.id.fg_my_items);
//        ((Button)myItems.getView().findViewById(R.id.btn_frg_my_items)).setText("button2");


        switch (getArguments().getInt(ARG_PAGE_NUMBER)) {
            case 0:
                text.setText(String.format("This is 0 page?"));
                break;
            case 1:
                text.setText(String.format("This is one page?"));
                break;
            case 2:
                text.setText(String.format("This is second page?"));
                break;
            case 3:
                text.setText(String.format("This is tree page?"));
                break;
            case 4:
                text.setText(String.format("This is for page?"));
                break;
            default:
                text.setText(String.format("This is SPARTAAA!!!!1111"));
                break;
        }


//        int page = getArguments().getInt(ARG_PAGE_NUMBER, -1);
//        text.setText(String.format("Pageeee %d", page));

        return rootView;
    }
}
