package com.wlht.oa.bean;


import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Default;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by hr on 16/3/31.
 */
@Table("user")
public class User implements Serializable
{

    private String birthday;

    private String profession;

    private String income;

    private String address;

    private String signature;

    private String name;

    private String email;

    private int gender;

    private String headImg;

    private String integralDeadLine;

    private String level;

    private String mobile;

    private String userName;

    private int integralAmount;

    private String phone;

    private String createTime;

    protected String id;

    public void setGender(int gender){
        this.gender = gender;
    }
    public int getGender(){
        return this.gender;
    }
    public void setHeadImg(String headImg){
        this.headImg = headImg;
    }
    public String getHeadImg(){
        return this.headImg == null?"":headImg;
    }
    public void setIntegralDeadLine(String integralDeadLine){
        this.integralDeadLine = integralDeadLine;
    }
    public String getIntegralDeadLine(){
        return this.integralDeadLine;
    }
    public void setLevel(String level){
        this.level = level;
    }
    public String getLevel(){
        return this.level;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    public String getMobile(){
        return this.mobile;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setIntegralAmount(int integralAmount){
        this.integralAmount = integralAmount;
    }
    public int getIntegralAmount(){
        return this.integralAmount;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }
    public String getCreateTime(){
        return this.createTime;
    }

    public String getBirthday() {
        return birthday == null ? "":birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProfession() {
        return profession == null ? "":profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getIncome() {
        return income == null ? "":income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getAddress() {
        return address == null ? "" :address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSignature() {
        return signature == null ? "":signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getName() {
        return name == null ? "":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email == null ? "":email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (gender != user.gender) return false;
        if (integralAmount != user.integralAmount) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null)
            return false;
        if (profession != null ? !profession.equals(user.profession) : user.profession != null)
            return false;
        if (income != null ? !income.equals(user.income) : user.income != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (signature != null ? !signature.equals(user.signature) : user.signature != null)
            return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (headImg != null ? !headImg.equals(user.headImg) : user.headImg != null) return false;
        if (integralDeadLine != null ? !integralDeadLine.equals(user.integralDeadLine) : user.integralDeadLine != null)
            return false;
        if (level != null ? !level.equals(user.level) : user.level != null) return false;
        if (mobile != null ? !mobile.equals(user.mobile) : user.mobile != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null)
            return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (createTime != null ? !createTime.equals(user.createTime) : user.createTime != null)
            return false;
        return !(id != null ? !id.equals(user.id) : user.id != null);

    }

    @Override
    public int hashCode() {
        int result = birthday != null ? birthday.hashCode() : 0;
        result = 31 * result + (profession != null ? profession.hashCode() : 0);
        result = 31 * result + (income != null ? income.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (signature != null ? signature.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + gender;
        result = 31 * result + (headImg != null ? headImg.hashCode() : 0);
        result = 31 * result + (integralDeadLine != null ? integralDeadLine.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + integralAmount;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
