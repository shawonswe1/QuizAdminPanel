package com.queueit.quizadminpanel.ModelClass;

public class GetCategoryModel {
    String name;
    String mKey;

    public GetCategoryModel() {
    }

    public GetCategoryModel(String name, String mKey) {
        this.name = name;
        this.mKey = mKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }
}
