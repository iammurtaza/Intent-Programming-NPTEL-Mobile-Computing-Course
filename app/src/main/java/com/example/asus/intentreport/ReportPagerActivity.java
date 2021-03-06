package com.example.asus.intentreport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Created by Asus on 09-10-2016.
 */

public class ReportPagerActivity extends FragmentActivity {

    private static final String EXTRA_REPORT_ID = "com.example.asus.intentreport.report_id";

    private ViewPager mViewPager;
    private List<Report> mReports;

    public static Intent newIntent(Context packageContext, UUID reportId){
        Intent intent = new Intent(packageContext, ReportPagerActivity.class);
        intent.putExtra(EXTRA_REPORT_ID, reportId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_pager);

        UUID reportId= (UUID) getIntent()
                .getSerializableExtra(EXTRA_REPORT_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_report_pager_view_pager);

        mReports = ReportStore.get(this).getReports();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Report report = mReports.get(position);
                return ReportFragment.newInstance(report.getId());
            }

            @Override
            public int getCount() {
                return mReports.size();
            }
        });

        for(int i=0; i<mReports.size(); i++){
            if(mReports.get(i).getId().equals(reportId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
