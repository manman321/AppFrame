package com.wlht.oa.ui.approve;/**
 * Created by hr on 16/7/15.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.wlht.oa.R;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.bean.approve.TemplateField;
import com.wlht.oa.util.StringUtils;
import com.wlht.oa.util.TDevice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ApproveFormDetailFragment extends BaseTitleFragment {
    LinearLayout mFormDetailsView;
    ArrayList<TemplateField> mFields;
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_approve_form_row, null, false);
    }

    @Override
    public void initNavBar() {
        setHeaderTitle("明细");

        createRightImageView(R.drawable.gou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboardForCurrentFocus();
                for (int i = 0; i < mViews.size();i++)
                {
                    TextView textView = mViews.get(i);
                    TemplateField field = mFields.get(i);
                    field.data = textView.getText().toString();
                }
                getContext().popTopFragment(mFields);
            }
        });


    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mFormDetailsView = (LinearLayout) view.findViewById(R.id.formDetailsView);
        initFormField();

    }

    ArrayList<TextView> mViews = new ArrayList<>();

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private void initFormField()
    {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int i = 0; i < mFields.size();i++)
        {
            final TemplateField field = mFields.get(i);

            int resId = R.layout.view_approve_field;
            if (field.type == TemplateField.NUM || field.type == TemplateField.STRING)
            {
                resId = R.layout.view_approve_field_edit;
            }
            View view = inflater.inflate(resId, null, false);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,(int) TDevice.dpToPixel(44));
            params.topMargin =  2;
            mFormDetailsView.addView(view, params);
            view.setTag(field);

            TextView name = (TextView)view.findViewById(R.id.nameTv);
            final TextView value =(TextView)view.findViewById(R.id.valueTv);

            name.setText(field.displayName);
            value.setText(field.data);

            mViews.add(value);

            if (field.type == TemplateField.NUM)
            {
                value.setInputType(InputType.TYPE_CLASS_NUMBER);
            }else if (field.type == TemplateField.STRING)
            {

            }else
            {
                value.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        hideKeyboardForCurrentFocus();

                        if (field.type == TemplateField.OPTION) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setItems(field.options.toArray(new String[]{}), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    value.setText(field.options.get(which));
                                }
                            });
                            builder.show();

                        } else if (field.type == TemplateField.DATE) {

                            TimePickerView timePickerView = new TimePickerView(getContext(), TimePickerView.Type.ALL);
                            timePickerView.setRange(1990, 2020);
                            timePickerView.setCyclic(false);
                            timePickerView.setCancelable(true);
                            try
                            {
                                String birthday = value.getText().toString();
                                if (!StringUtils.isEmpty(birthday))timePickerView.setTime(format.parse(birthday));
                            }catch (Exception e){e.printStackTrace();}

                            timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                                @Override
                                public void onTimeSelect(Date date) {
                                    value.setText(format.format(date));
                                }
                            });
                            timePickerView.show();
                        }
                    }
                });
            }
        }
    }


    @Override
    public void onEnter(Object data) {
        super.onEnter(data);
        mFields = (ArrayList<TemplateField>)data;
    }

    @Override
    public void initEvent(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            default:
                break;
        }
    }

}
