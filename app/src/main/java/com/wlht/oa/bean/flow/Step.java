package com.wlht.oa.bean.flow;

import java.io.Serializable;
import java.util.List;

public class Step  implements Serializable {
    private String id;
    private String name;
    private String opinionDisplay;
    private String expiredPrompt;
    private String signatureType;
    private String workTime;
    private String limitTime;
    private String otherTime;
    private String archives;
    private String archivesParams;
    private String note;
    /**
     * x : 173
     * y : 114
     * width : 108
     * height : 50
     */

    private Position position;
    /**
     * flowType : 1
     * runSelect : 0
     * handlerType : 0
     * selectRange :
     * handlerStep :
     * valueField :
     * defaultHandler :
     * hanlderModel : 0
     * backModel : 0
     * backType : 0
     * backStep :
     * percentage :
     */

    private Behavior behavior;
    /**
     * submitBefore :
     * submitAfter :
     * backBefore :
     * backAfter :
     */

    private Event event;
    /**
     * id : b8a7bca3-9a32-45eb-b685-1e8c05c78bd3
     * name :
     * type : 719c6c30-3d6a-44e2-8730-637c651f1df7
     * srot : 0
     */

    private List<Form> forms;
    /**
     * id : 3b271f67-0433-4082-ad1a-8df1b967b879
     * sort : 0
     */

    private List<Button> buttons;
    /**
     * field : 06075250-30dc-4d32-bf97-e922cb30fac8.TempTest.ID
     * status : 0
     * check : 0
     */

    private List<FieldStatus> fieldStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpinionDisplay() {
        return opinionDisplay;
    }

    public void setOpinionDisplay(String opinionDisplay) {
        this.opinionDisplay = opinionDisplay;
    }

    public String getExpiredPrompt() {
        return expiredPrompt;
    }

    public void setExpiredPrompt(String expiredPrompt) {
        this.expiredPrompt = expiredPrompt;
    }

    public String getSignatureType() {
        return signatureType;
    }

    public void setSignatureType(String signatureType) {
        this.signatureType = signatureType;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime;
    }

    public String getOtherTime() {
        return otherTime;
    }

    public void setOtherTime(String otherTime) {
        this.otherTime = otherTime;
    }

    public String getArchives() {
        return archives;
    }

    public void setArchives(String archives) {
        this.archives = archives;
    }

    public String getArchivesParams() {
        return archivesParams;
    }

    public void setArchivesParams(String archivesParams) {
        this.archivesParams = archivesParams;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public List<FieldStatus> getFieldStatus() {
        return fieldStatus;
    }

    public void setFieldStatus(List<FieldStatus> fieldStatus) {
        this.fieldStatus = fieldStatus;
    }

}