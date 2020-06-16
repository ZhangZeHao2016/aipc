package com.yotop.aipc.model;

import javax.persistence.*;

@Table(name = "ai_user")
public class AiUser {
    /**
     * 用户ID
     */
    @Id
    @Column(name = "U_ID")
    private Integer uId;

    @Column(name = "U_NAME")
    private String uName;

    @Column(name = "U_PASSWORD")
    private String uPassword;

    /**
     * 获取用户ID
     *
     * @return U_ID - 用户ID
     */
    public Integer getuId() {
        return uId;
    }

    /**
     * 设置用户ID
     *
     * @param uId 用户ID
     */
    public void setuId(Integer uId) {
        this.uId = uId;
    }

    /**
     * @return U_NAME
     */
    public String getuName() {
        return uName;
    }

    /**
     * @param uName
     */
    public void setuName(String uName) {
        this.uName = uName;
    }

    /**
     * @return U_PASSWORD
     */
    public String getuPassword() {
        return uPassword;
    }

    /**
     * @param uPassword
     */
    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }
}