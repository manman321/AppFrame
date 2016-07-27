package com.wlht.oa.bean.approve;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hr on 16/7/13.
 */
public class TemplateField implements Serializable,Cloneable {

    public static final int NUM = 1;

    public static final int DATE = 1 << 1;

    public static final int STRING = 1 << 2;

    public static final int OPTION = 1 << 3;



    public String id;
    public String displayName;
    public String name;
    public String data;
    public String maxValue;
    public String minValue;
    public boolean mustInput;
    public Integer type;
    public double dataSum = 0.0D;
    public List<String> options;





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public boolean isMustInput() {
        return mustInput;
    }

    public void setMustInput(boolean mustInput) {
        this.mustInput = mustInput;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public double getDataSum() {
        return dataSum;
    }

    public void setDataSum(double dataSum) {
        this.dataSum = dataSum;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TemplateField that = (TemplateField) o;

        if (mustInput != that.mustInput) return false;
        if (Double.compare(that.dataSum, dataSum) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (maxValue != null ? !maxValue.equals(that.maxValue) : that.maxValue != null)
            return false;
        if (minValue != null ? !minValue.equals(that.minValue) : that.minValue != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return !(options != null ? !options.equals(that.options) : that.options != null);

    }


    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (maxValue != null ? maxValue.hashCode() : 0);
        result = 31 * result + (minValue != null ? minValue.hashCode() : 0);
        result = 31 * result + (mustInput ? 1 : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        temp = Double.doubleToLongBits(dataSum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (options != null ? options.hashCode() : 0);
        return result;
    }
}
