package com.example.asus.intentreport;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Asus on 07-10-2016.
 */

public class ReportStore {
    private static ReportStore sReportStore;
    private List<Report> mReports;

    public static ReportStore get(Context context){
        if(sReportStore == null){
            sReportStore = new ReportStore(context);
        }
        return sReportStore;
    }

    private ReportStore(Context context){
        mReports = new ArrayList<>();
        for(int i=0; i<100 ; i++){
            Report report = new Report();
            report.setTitle("Report #" + i);
            report.setResolved(i%2 == 0);
            mReports.add(report);
        }
    }

    public List<Report> getReports(){
        return mReports;
    }

    public  Report getReport(UUID id){
        for (Report report : mReports) {
            if(report.getId().equals(id)) {
                return report;
            }
        }
        return null;
    }
}
