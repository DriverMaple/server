package com.maple.guideserver.Common;

public class ActType {
    private String user_id;//用户id
    private String account;//账号
    private String password;//密码
    private String longitude;//经度
    private String latitude;//纬度
    private String head_jpg;//头像
    private String motto;//个性签名
    private String team_id;//队伍id
    private String team_name;//队名
    private String team_pw;//口令

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_pw() {
        return team_pw;
    }

    public void setTeam_pw(String team_pw) {
        this.team_pw = team_pw;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getHead_jpg() {
        return head_jpg;
    }

    public void setHead_jpg(String head_jpg) {
        this.head_jpg = head_jpg;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
