package com.example.asus.intentreport;

import android.support.v4.app.Fragment;

/**
 * Created by Asus on 07-10-2016.
 */

public class ReportListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new ReportListFragment();
    }
}
