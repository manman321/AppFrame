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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.wlht.oa.R;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.bean.approve.Approve;
import com.wlht.oa.bean.approve.Template;
import com.wlht.oa.bean.approve.TemplateField;
import com.wlht.oa.bean.flow.Flow;
import com.wlht.oa.util.ImageUtils;
import com.wlht.oa.util.StringUtils;
import com.wlht.oa.util.TDevice;
import com.wlht.oa.widget.FlowView;
import com.wlht.oa.widget.HScrollView;
import com.wlht.oa.widget.InfoViewRow;
import com.wlht.oa.widget.ModuleLinkAddView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ApproveCreateFragment extends BaseTitleFragment {
    TextView mDescriptionTv;
    LinearLayout mAttachLL;
    TextView mFormOptionsDelTv;
    LinearLayout mFormOptionsDel;
    LinearLayout mFormOptionsAdd;
    LinearLayout mFormOptions;
    LinearLayout mFormDetailsView;
    LinearLayout mFormDetails;
    LinearLayout mFormFields;

    LinearLayout layout_info_file,layout_modulelink_task,layout_modulelink_approve;
    HScrollView  mScrollView;
    FlowView mFlowView;
    Template mTemplate;
    ModuleLinkAddView mModuleLinkAddView;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_approve, null, false);
    }

    @Override
    public void initNavBar() {
        setHeaderTitle("创建审批");
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mFormFields = (LinearLayout) view.findViewById(R.id.formFields);
        mFormDetails = (LinearLayout) view.findViewById(R.id.formDetails);
        mFormDetailsView = (LinearLayout) view.findViewById(R.id.formDetailsView);
        mFormOptions = (LinearLayout) view.findViewById(R.id.formOptions);
        mFormOptionsAdd = (LinearLayout) view.findViewById(R.id.formOptionsAdd);
        mFormOptionsDel = (LinearLayout) view.findViewById(R.id.formOptionsDel);
        mFormOptionsDelTv = (TextView) view.findViewById(R.id.formOptionsDelTv);
        mAttachLL = (LinearLayout) view.findViewById(R.id.attachLL);
        mDescriptionTv = (TextView) view.findViewById(R.id.descriptionTv);
        mScrollView = (HScrollView)view.findViewById(R.id.scrollView);
        mFlowView = (FlowView)view.findViewById(R.id.flowView);
        layout_info_file = (LinearLayout)view.findViewById(R.id.layout_info_file);
        layout_modulelink_task = (LinearLayout)view.findViewById(R.id.layout_modulelink_task);
        layout_modulelink_approve = (LinearLayout)view.findViewById(R.id.layout_modulelink_approve);
        mModuleLinkAddView = (ModuleLinkAddView)view.findViewById(R.id.moduleLinkAddView);
        initFormField();
        initFormDetail();



    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private void initFormField()
    {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int i = 0; i < mTemplate.fields.size();i++)
        {
            final TemplateField field = mTemplate.fields.get(i);

            int resId = R.layout.view_approve_field;
            if (field.type == TemplateField.NUM || field.type == TemplateField.STRING)
            {
                resId = R.layout.view_approve_field_edit;
            }
            View view = inflater.inflate(resId, null, false);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,(int) TDevice.dpToPixel(44));
            params.topMargin =  2;
            mFormFields.addView(view,params);
            view.setTag(field);

            TextView name = (TextView)view.findViewById(R.id.nameTv);
            final TextView value =(TextView)view.findViewById(R.id.valueTv);

            name.setText(field.displayName);
            value.setText(field.data);

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

    private void initFormDetail()
    {
        InfoViewRow row = new InfoViewRow(getContext());
        row.initWithColumn(mTemplate.detailFields.size());
        for (int i = 0; i < mTemplate.detailFields.size();i++)
        {
            ArrayList<TextView> views = row.getViews();
            TemplateField field = mTemplate.detailFields.get(i);
            TextView textView = views.get(i);
            textView.setText(field.displayName);
        }
        mFormDetailsView.addView(row);
    }


    @Override
    public void initEvent(View view) {
        mFormOptionsAdd.setOnClickListener(this);
        mFormOptionsDel.setOnClickListener(this);
        mScrollView.setOnScrollListener(new HScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollX) {
                int count = mFormDetailsView.getChildCount();
                for (int i = 1; i < count; i++) {
                    InfoViewRow row = (InfoViewRow) mFormDetailsView.getChildAt(i);
                    row.scrollX(scrollX);
                }
            }
        });
    }



    @Override
    public void initData() {
        InputStream inputStream = getResources().openRawResource(R.raw.json);
        InputStreamReader reader = new InputStreamReader(inputStream);
        Flow flow = new Gson().fromJson(reader, Flow.class);
        mFlowView.initWithFlow(flow);
    }


    private void addNewRow(final ArrayList<TemplateField> datas)
    {
        final InfoViewRow row = new InfoViewRow(getContext());
        row.initWithColumn(datas.size());
        for (int i = 0; i < datas.size();i++)
        {
            ArrayList<TextView> views = row.getViews();
            TemplateField field = datas.get(i);
            TextView textView = views.get(i);
            textView.setText(field.data);
        }
        row.setTag(datas);
        mFormDetailsView.addView(row);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(ApproveFormDetailFragment.class, datas);
            }
        });
        row.setOnDeleteClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFormDetailsView.removeView(row);
            }
        });
    }

    private void updateRow(InfoViewRow row,ArrayList<TemplateField> datas)
    {
        if (datas.equals(row.getTag()))
        {
            for (int i = 0; i < row.getViews().size();i++)
            {
                TextView textView = row.getViews().get(i);
                TemplateField field = datas.get(i);
                textView.setText(field.data);
            }
        }
    }


    private void associationApprove(ArrayList<Approve> datas)
    {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        layout_modulelink_approve.setVisibility(View.VISIBLE);
        for (int i = 0;  i < datas.size();i++)
        {
            Approve approve = datas.get(i);
            View view =  inflater.inflate(R.layout.list_cell_audit, null, false);
            ImageView  photoIv = (ImageView) view.findViewById(R.id.photoIv);
            TextView  nameTv = (TextView) view.findViewById(R.id.nameTv);
            TextView   auditTv = (TextView) view.findViewById(R.id.auditTv);
            TextView   typeTv = (TextView) view.findViewById(R.id.typeTv);
            TextView  timeTv = (TextView) view.findViewById(R.id.timeTv);
            ImageView  statusIv = (ImageView) view.findViewById(R.id.statusIv);
            ImageView  delIv = (ImageView)view.findViewById(R.id.delIv);
            Picasso.with(getContext()).load(ImageUtils.imageUrl(approve.logoUrl)).into(photoIv);
            delIv.setVisibility(View.VISIBLE);
            nameTv.setText(approve.userName);
            typeTv.setText(approve.typeName);
            timeTv.setText(approve.time);
            statusIv.setImageResource(R.drawable.sp_icon_stamp_dwp);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.topMargin = 1;
            layout_modulelink_approve.addView(view,params);
        }
    }

    @Override
    public void onBackWithData(Object data) {
        super.onBackWithData(data);

        ArrayList list = (ArrayList)data;
        if (list == null || list.size() == 0)return;
        Object value = list.get(0);

        if (value instanceof TemplateField)
        {
            ArrayList<TemplateField> rowData = (ArrayList<TemplateField>)data;
            if (StringUtils.isEmpty(rowData.get(0).id))
            {
                addNewRow(rowData);
            }else
            {
                int count = mFormDetailsView.getChildCount();
                for (int i = 1; i < count;i++)
                {
                    InfoViewRow row = (InfoViewRow)mFormDetailsView.getChildAt(i);
                    if (rowData.equals(row.getTag()))
                    {
                        updateRow(row,rowData);
                    }
                }
            }
        }else if (value instanceof Approve)
        {
            associationApprove((ArrayList<Approve>)data);
            mModuleLinkAddView.hide();
        }
    }

    @Override
    public void onEnter(Object data) {
        super.onEnter(data);

        mTemplate = new Template();
        mTemplate.title = "请假申请";
        mTemplate.categoryName = "日常类";
        mTemplate.describe = "这是一张请假条";

        ArrayList<TemplateField> fields = new ArrayList<>();

        TemplateField stringField = new TemplateField();
        stringField.displayName = "申请原因";
        stringField.mustInput = true;
        stringField.type = TemplateField.STRING;
        fields.add(stringField);

        TemplateField dateField = new TemplateField();
        dateField.displayName = "开始时间";
        dateField.mustInput = true;
        dateField.type = TemplateField.DATE;
        fields.add(dateField);

        TemplateField numField = new TemplateField();
        numField.displayName = "请假天数";
        numField.mustInput = true;
        numField.type = TemplateField.NUM;
        fields.add(numField);


        TemplateField optionField = new TemplateField();
        optionField.displayName = "请假类别";
        optionField.mustInput = true;
        optionField.type = TemplateField.OPTION;
        optionField.options = Arrays.asList("事假","病假","其他");
        fields.add(optionField);

        mTemplate.fields = fields;
        mTemplate.detailFields = fields;

    }





    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.formOptionsAdd:
            {
                ArrayList<TemplateField> row = new ArrayList<TemplateField>(mTemplate.detailFields);
                getContext().pushFragmentToBackStack(ApproveFormDetailFragment.class,row);
            }
                break;

            case R.id.formOptionsDel:
            {
                int count = mFormDetailsView.getChildCount();
                for (int i = 1; i < count;i ++)
                {
                    InfoViewRow row = (InfoViewRow)mFormDetailsView.getChildAt(i);
                    row.toggleDeleteVisibility();
                }
            }
                break;
            default:
                break;
        }
    }

}
