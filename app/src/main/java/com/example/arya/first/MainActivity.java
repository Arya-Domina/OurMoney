package com.example.arya.first;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    private TabHost tabHost;
    private final String TAG_HISTORY = "History", TAG_MY_ITEMS = "My items", TAG_FRIENDS = "Friends", TAG_CALCULATION = "Calculation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        tabHost = (TabHost)findViewById(R.id.tabhost);
//        tabHost.setup();
        tabHost = getTabHost();
        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec(TAG_HISTORY);
        tabSpec.setIndicator(null, getResources().getDrawable(R.drawable.tab_icon_selector_history));
        tabSpec.setContent(TabFactory);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(TAG_MY_ITEMS);
        tabSpec.setIndicator(null, getResources().getDrawable(R.drawable.tab_icon_selector_history));
        tabSpec.setContent(TabFactory);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(TAG_FRIENDS);
        tabSpec.setIndicator(null, getResources().getDrawable(R.drawable.tab_icon_selector_history));
        tabSpec.setContent(TabFactory);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(TAG_CALCULATION);
        tabSpec.setIndicator(null, getResources().getDrawable(R.drawable.tab_icon_selector_history));
        tabSpec.setContent(TabFactory);
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTabByTag(TAG_MY_ITEMS);
/*
        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setIndicator("Taaab3!");
//        View view = getLayoutInflater().inflate(R.layout.activity_history, null);
//        tabSpec.setIndicator(view);
        tabSpec.setContent(R.id.tab_text3);
        tabHost.addTab(tabSpec);


        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                Toast.makeText(getBaseContext(), "tabId = " + tabId, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    TabHost.TabContentFactory TabFactory = new TabHost.TabContentFactory() {
        @Override
        public View createTabContent(String tag) {
            if (tag == TAG_HISTORY) {
//                TextView textView = new TextView(MainActivity.this);
//                textView.setText("omnomnom");
//                return textView;
                return getLayoutInflater().inflate(R.layout.activity_history, null);
            } else if (tag == TAG_MY_ITEMS) {
                return getLayoutInflater().inflate(R.layout.activity_my_items, null);
            } else if (tag == TAG_FRIENDS) {
                return getLayoutInflater().inflate(R.layout.activity_friends, null);
            }else if (tag == TAG_CALCULATION) {
                return getLayoutInflater().inflate(R.layout.activity_calculation, null);
            }
            return null;
        }
    };

}
