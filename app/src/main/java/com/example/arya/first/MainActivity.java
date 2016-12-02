package com.example.arya.first;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity {

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        tabHost = (TabHost)findViewById(R.id.tabhost);
//        tabHost.setup();
        tabHost = getTabHost();
        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("Taaab1!");
        tabSpec.setContent(TabFactory);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator(null, getResources().getDrawable(R.drawable.tab_icon_selector));
        tabSpec.setContent(TabFactory);
        tabHost.addTab(tabSpec);
/*
        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setIndicator("Taaab3!");
//        View view = getLayoutInflater().inflate(R.layout.activity_history, null);
//        tabSpec.setIndicator(view);
        tabSpec.setContent(R.id.tab_text3);
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTabByTag("tag2");

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                Toast.makeText(getBaseContext(), "tabId = " + tabId, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    TabHost.TabContentFactory TabFactory = new TabHost.TabContentFactory() {
        @Override
        public View createTabContent(String tag) {
            if (tag == "tag1") {
                return getLayoutInflater().inflate(R.layout.tab, null);
            } else if (tag == "tag2") {
                TextView textView = new TextView(MainActivity.this);
                textView.setText("omnomnom");
                return textView;
            }
            return null;
        }
    };

}
