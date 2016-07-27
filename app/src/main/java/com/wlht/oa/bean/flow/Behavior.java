package com.wlht.oa.bean.flow;

import java.io.Serializable;

public class Behavior  implements Serializable {
    private String flowType;
    private String runSelect;
    private String handlerType;
    private String selectRange;
    private String handlerStep;
    private String valueField;
    private String defaultHandler;
    private String hanlderModel;
    private String backModel;
    private String backType;
    private String backStep;
    private String percentage;

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getRunSelect() {
        return runSelect;
    }

    public void setRunSelect(String runSelect) {
        this.runSelect = runSelect;
    }

    public String getHandlerType() {
        return handlerType;
    }

    public void setHandlerType(String handlerType) {
        this.handlerType = handlerType;
    }

    public String getSelectRange() {
        return selectRange;
    }

    public void setSelectRange(String selectRange) {
        this.selectRange = selectRange;
    }

    public String getHandlerStep() {
        return handlerStep;
    }

    public void setHandlerStep(String handlerStep) {
        this.handlerStep = handlerStep;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }

    public String getDefaultHandler() {
        return defaultHandler;
    }

    public void setDefaultHandler(String defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    public String getHanlderModel() {
        return hanlderModel;
    }

    public void setHanlderModel(String hanlderModel) {
        this.hanlderModel = hanlderModel;
    }

    public String getBackModel() {
        return backModel;
    }

    public void setBackModel(String backModel) {
        this.backModel = backModel;
    }

    public String getBackType() {
        return backType;
    }

    public void setBackType(String backType) {
        this.backType = backType;
    }

    public String getBackStep() {
        return backStep;
    }

    public void setBackStep(String backStep) {
        this.backStep = backStep;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
