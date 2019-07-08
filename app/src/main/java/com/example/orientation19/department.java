package com.example.orientation19;

public class department {
    private String dept;
    private int mresource;

    public department(String vname,int res)
    {
        dept=vname;
        mresource=res;
    }
    public String getDept(){
        return dept;
    }
    public int getMresource(){
        return mresource;
    }
}
