package com.wlht.oa.bean;

import java.io.Serializable;

/**
 * Created by hr on 16/7/1.
 */
public class Food implements Serializable {
    public String imageUrl;
    public String name;
    public boolean isSelected;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (isSelected != food.isSelected) return false;
        if (imageUrl != null ? !imageUrl.equals(food.imageUrl) : food.imageUrl != null)
            return false;
        return !(name != null ? !name.equals(food.name) : food.name != null);

    }

    @Override
    public int hashCode() {
        int result = imageUrl != null ? imageUrl.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isSelected ? 1 : 0);
        return result;
    }
}
