package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.context.properties.bind.DataObjectPropertyName;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Member  {




        private int id;  // 假设会员卡号是唯一的标识符，并且长度不超过20个字符
        private String memberName;  // 姓名可能较长，这里设置为最多50个字符
        private char gender;  // 'M' 或 'F' 等单个字符表示



        private String birthDate;  // 使用标准日期格式存储
        private String contactAddress;  // 地址可能会比较长，这里提供足够的空间
        private String phoneNumber;  // 包括国家代码在内的电话号码
        private String occupation;  // 职业描述，假设不会太长
        private String idNumber;  // 中国大陆身份证号码固定18位

    private String joinTime;  // 自动记录成员加入的时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }


    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }
}
