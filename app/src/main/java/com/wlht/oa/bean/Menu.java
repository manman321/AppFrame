package com.wlht.oa.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hr on 16/7/21.
 */
public class Menu implements Serializable{


    /**
     * id : ceae1645-9785-45e1-822e-f082db3ea902
     * title : 管理目录
     * ico :
     * icoName :
     * link :
     * model :
     * width :
     * height :
     * hasChilds : 1
     * childs : []
     */

    private String id;
    private String title;
    private String ico;
    private String icoName;
    private String link;
    private String model;
    private String hasChilds;
    private List<Menu> childs;

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

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getIcoName() {
        return icoName;
    }

    public void setIcoName(String icoName) {
        this.icoName = icoName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getHasChilds() {
        return hasChilds;
    }

    public void setHasChilds(String hasChilds) {
        this.hasChilds = hasChilds;
    }

    public List<Menu> getChilds() {
        return childs;
    }

    public void setChilds(List<Menu> childs) {
        this.childs = childs;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (id != null ? !id.equals(menu.id) : menu.id != null) return false;
        if (title != null ? !title.equals(menu.title) : menu.title != null) return false;
        if (ico != null ? !ico.equals(menu.ico) : menu.ico != null) return false;
        if (icoName != null ? !icoName.equals(menu.icoName) : menu.icoName != null) return false;
        if (link != null ? !link.equals(menu.link) : menu.link != null) return false;
        if (model != null ? !model.equals(menu.model) : menu.model != null) return false;
        if (hasChilds != null ? !hasChilds.equals(menu.hasChilds) : menu.hasChilds != null)
            return false;
        return !(childs != null ? !childs.equals(menu.childs) : menu.childs != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (ico != null ? ico.hashCode() : 0);
        result = 31 * result + (icoName != null ? icoName.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (hasChilds != null ? hasChilds.hashCode() : 0);
        result = 31 * result + (childs != null ? childs.hashCode() : 0);
        return result;
    }
}
