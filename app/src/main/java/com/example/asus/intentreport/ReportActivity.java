package com.example.asus.intentreport;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;


import java.util.UUID;

public class ReportActivity extends SingleFragmentActivity{

    private static final String EXTRA_REPORT_id = "com.example.asus.intentreport.report_id";


    public static Intent newIntent(Context packageContext, UUID reportId){
        Intent intent = new Intent(packageContext, ReportActivity.class);
        intent.putExtra(EXTRA_REPORT_id, reportId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        UUID reportId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_REPORT_id);
        return ReportFragment.newInstance(reportId);
        //return new ReportFragment();
    }
}