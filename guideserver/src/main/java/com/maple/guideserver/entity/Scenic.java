package com.maple.guideserver.entity;

/**
 * Created by Maple on 2017/11/16.
 */
public class Scenic {
    private Integer scenic_id;
    private String  scenic_pic;
    private String scenic_name;
    private String scenic_introduct;

    public Integer getScenic_id() {
        return scenic_id;
    }

    public void setScenic_id(Integer scenic_id) {
        this.scenic_id = scenic_id;
    }

    public String getScenic_pic() {
        return scenic_pic;
    }

    public void setScenic_pic(String scenic_pic) {
        this.scenic_pic = scenic_pic;
    }

    public String getScenic_name() {
        return scenic_name;
    }

    public void setScenic_name(String scenic_name) {
        this.scenic_name = scenic_name;
    }

    public String getScenic_introduct() {
        return scenic_introduct;
    }

    public void setScenic_introduct(String scenic_introduct) {
        this.scenic_introduct = scenic_introduct;
    }
}
