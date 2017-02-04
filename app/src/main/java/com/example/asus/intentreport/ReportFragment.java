package com.example.asus.intentreport;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Asus on 05-10-2016.
 */

public class ReportFragment extends Fragment {
    private static final String ARG_REPORT_ID = "report_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;

    private Report mReport;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mResolvedCheckBox;

    public static ReportFragment newInstance(UUID reportID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_REPORT_ID, reportID);

        ReportFragment fragment = new ReportFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //UUID reportId = (UUID) getActivity().getIntent().getSerializableExtra(ReportActivity.EXTRA_REPORT_id);
        UUID reportId = (UUID) getArguments().getSerializable(ARG_REPORT_ID);
        mReport = ReportStore.get(getActivity()).getReport(reportId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState){
        View v = inflater.inflate(R.layout.fragment_report, container, false);
        mTitleField = (EditText) v.findViewById(R.id.report_title);
        mTitleField.setText(mReport.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mReport.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button) v.findViewById(R.id.report_date);
        mDateButton.setText(mReport.getDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getFragmentManager();
                //DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mReport.getDate());
                dialog.setTargetFragment(ReportFragment.this , REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mResolvedCheckBox = (CheckBox) v.findViewById(R.id.report_resolved);
        mResolvedCheckBox.setChecked(mReport.isResolved());
        mResolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mReport.setResolved(isChecked);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mReport.setDate(date);
            mDateButton.setText(mReport.getDate().toString());
        }
    }
}
