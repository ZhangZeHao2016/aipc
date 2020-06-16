package com.yotop.aipc.model;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;

@Table(name = "ai_pdffile")
public class AiPdffile {
    /**
     * 主键
     */
    @Id
    @Column(name = "P_ID",insertable = false)
    @KeySql(useGeneratedKeys = true)
    private Integer pId;

    /**
     * 文件名称
     */
    @Column(name = "P_FILENAME")
    private String pFilename;

    /**
     * 文件原名称
     */
    @Column(name = "P_REALNAME")
    private String pRealname;

    /**
     * 上传时间
     */
    @Column(name = "P_SUBMITDATE")
    private String pSubmitdate;

    /**
     * 上传人
     */
    @Column(name = "P_SUBMITPERSON")
    private String pSubmitperson;

    /**
     * 是否转换成功 1转换成功 0转换失败
     */
    @Column(name = "P_ISCHANGESUCCESS")
    private Integer pIschangesuccess;

    /**
     * 获取主键
     *
     * @return P_ID - 主键
     */
    public Integer getpId() {
        return pId;
    }

    /**
     * 设置主键
     *
     * @param pId 主键
     */
    public void setpId(Integer pId) {
        this.pId = pId;
    }

    /**
     * 获取文件名称
     *
     * @return P_FILENAME - 文件名称
     */
    public String getpFilename() {
        return pFilename;
    }

    /**
     * 设置文件名称
     *
     * @param pFilename 文件名称
     */
    public void setpFilename(String pFilename) {
        this.pFilename = pFilename;
    }

    /**
     * 获取文件原名称
     *
     * @return P_REALNAME - 文件原名称
     */
    public String getpRealname() {
        return pRealname;
    }

    /**
     * 设置文件原名称
     *
     * @param pRealname 文件原名称
     */
    public void setpRealname(String pRealname) {
        this.pRealname = pRealname;
    }

    /**
     * 获取上传时间
     *
     * @return P_SUBMITDATE - 上传时间
     */
    public String getpSubmitdate() {
        return pSubmitdate;
    }

    /**
     * 设置上传时间
     *
     * @param pSubmitdate 上传时间
     */
    public void setpSubmitdate(String pSubmitdate) {
        this.pSubmitdate = pSubmitdate;
    }

    /**
     * 获取上传人
     *
     * @return P_SUBMITPERSON - 上传人
     */
    public String getpSubmitperson() {
        return pSubmitperson;
    }

    /**
     * 设置上传人
     *
     * @param pSubmitperson 上传人
     */
    public void setpSubmitperson(String pSubmitperson) {
        this.pSubmitperson = pSubmitperson;
    }

    /**
     * 获取是否转换成功 1转换成功 0转换失败
     *
     * @return P_ISCHANGESUCCESS - 是否转换成功 1转换成功 0转换失败
     */
    public Integer getpIschangesuccess() {
        return pIschangesuccess;
    }

    /**
     * 设置是否转换成功 1转换成功 0转换失败
     *
     * @param pIschangesuccess 是否转换成功 1转换成功 0转换失败
     */
    public void setpIschangesuccess(Integer pIschangesuccess) {
        this.pIschangesuccess = pIschangesuccess;
    }
}