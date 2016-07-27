package com.wlht.oa.bean.approve;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hr on 16/7/13.
 */
public class Catalog implements Serializable {

    public String id;
    public String name;
    public boolean isShowType = false;
    public ArrayList<Template> templates;

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

    public boolean isShowType() {
        return isShowType;
    }

    public void setIsShowType(boolean isShowType) {
        this.isShowType = isShowType;
    }

    public ArrayList<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(ArrayList<Template> templates) {
        this.templates = templates;
    }
}
