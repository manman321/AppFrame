package com.wlht.oa.bean.approve;

import com.wlht.oa.base.BaseEntity;
import com.wlht.oa.bean.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by hr on 16/7/13.
 */
public class Template extends BaseEntity.ListBean {

    public String id;
    //模板标题
    public String title;
    //模板类型
    public String categoryName;
    //模板类型的id
    public String categoryId;
    //表单明细
    public ArrayList<TemplateField> detailFields;


    //表单表头项
    public ArrayList<TemplateField> fields;
    //审批完成后添加字段
    public ArrayList<TemplateField> afterApprovedFields;
    //审批类型  固定流程/自由流程
    public int flowType;
    //单据说明: 模板通用说明,解释给填写单据的人
    public String describe;
    //知会人,流程结束后通知xxx
    public ArrayList<User> notifiers;
    //固定流程审批人
    public ArrayList<User> fixedFlowApprover;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public ArrayList<TemplateField> getDetailFields() {
        return detailFields;
    }

    public void setDetailFields(ArrayList<TemplateField> detailFields) {
        this.detailFields = detailFields;
    }

    public ArrayList<TemplateField> getFields() {
        return fields;
    }

    public void setFields(ArrayList<TemplateField> fields) {
        this.fields = fields;
    }

    public ArrayList<TemplateField> getAfterApprovedFields() {
        return afterApprovedFields;
    }

    public void setAfterApprovedFields(ArrayList<TemplateField> afterApprovedFields) {
        this.afterApprovedFields = afterApprovedFields;
    }

    public int getFlowType() {
        return flowType;
    }

    public void setFlowType(int flowType) {
        this.flowType = flowType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public ArrayList<User> getNotifiers() {
        return notifiers;
    }

    public void setNotifiers(ArrayList<User> notifiers) {
        this.notifiers = notifiers;
    }

    public ArrayList<User> getFixedFlowApprover() {
        return fixedFlowApprover;
    }

    public void setFixedFlowApprover(ArrayList<User> fixedFlowApprover) {
        this.fixedFlowApprover = fixedFlowApprover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Template template = (Template) o;

        if (flowType != template.flowType) return false;
        if (id != null ? !id.equals(template.id) : template.id != null) return false;
        if (title != null ? !title.equals(template.title) : template.title != null) return false;
        if (categoryName != null ? !categoryName.equals(template.categoryName) : template.categoryName != null)
            return false;
        if (categoryId != null ? !categoryId.equals(template.categoryId) : template.categoryId != null)
            return false;
        if (detailFields != null ? !detailFields.equals(template.detailFields) : template.detailFields != null)
            return false;
        if (fields != null ? !fields.equals(template.fields) : template.fields != null)
            return false;
        if (afterApprovedFields != null ? !afterApprovedFields.equals(template.afterApprovedFields) : template.afterApprovedFields != null)
            return false;
        if (describe != null ? !describe.equals(template.describe) : template.describe != null)
            return false;
        if (notifiers != null ? !notifiers.equals(template.notifiers) : template.notifiers != null)
            return false;
        return !(fixedFlowApprover != null ? !fixedFlowApprover.equals(template.fixedFlowApprover) : template.fixedFlowApprover != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (detailFields != null ? detailFields.hashCode() : 0);
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        result = 31 * result + (afterApprovedFields != null ? afterApprovedFields.hashCode() : 0);
        result = 31 * result + flowType;
        result = 31 * result + (describe != null ? describe.hashCode() : 0);
        result = 31 * result + (notifiers != null ? notifiers.hashCode() : 0);
        result = 31 * result + (fixedFlowApprover != null ? fixedFlowApprover.hashCode() : 0);
        return result;
    }

}
