package com.yotop.aipc.model;

import javax.persistence.*;

@Table(name = "ai_cmmdata")
public class AiCmmdata {
    @Id
    @Column(name = "C_ID")
    private Integer cId;

    /**
     * 关联PDF文件主键
     */
    @Column(name = "C_P_ID")
    private Integer cPId;

    /**
     * 所属figuer
     */
    @Column(name = "C_FIGUER")
    private String cFiguer;

    /**
     * 是否图示
     */
    @Column(name = "C_ISFIG")
    private String cIsfig;

    /**
     * 项目号
     */
    @Column(name = "C_ITEM")
    private String cItem;

    /**
     * 件号
     */
    @Column(name = "C_PARTNUMBER")
    private String cPartnumber;

    /**
     * 层级
     */
    @Column(name = "C_TIER")
    private Integer cTier;

    /**
     * 说明
     */
    @Column(name = "C_NOMENCLATURE")
    private String cNomenclature;

    /**
     * 有效性代码
     */
    @Column(name = "C_EDDCODE")
    private String cEddcode;

    /**
     * 数量
     */
    @Column(name = "C_UPA")
    private String cUpa;

    /**
     * 连接件
     */
    @Column(name = "C_CON")
    private Integer cCon;

    /**
     * 修改日期
     */
    @Column(name = "C_UPDATEDATE")
    private String cUpdatedate;

    /**
     * 修改人
     */
    @Column(name = "C_UPDATEPERSON")
    private String cUpdateperson;

    /**
     * @return C_ID
     */
    public Integer getcId() {
        return cId;
    }

    /**
     * @param cId
     */
    public void setcId(Integer cId) {
        this.cId = cId;
    }

    /**
     * 获取关联PDF文件主键
     *
     * @return C_P_ID - 关联PDF文件主键
     */
    public Integer getcPId() {
        return cPId;
    }

    /**
     * 设置关联PDF文件主键
     *
     * @param cPId 关联PDF文件主键
     */
    public void setcPId(Integer cPId) {
        this.cPId = cPId;
    }

    /**
     * 获取所属figuer
     *
     * @return C_FIGUER - 所属figuer
     */
    public String getcFiguer() {
        return cFiguer;
    }

    /**
     * 设置所属figuer
     *
     * @param cFiguer 所属figuer
     */
    public void setcFiguer(String cFiguer) {
        this.cFiguer = cFiguer;
    }

    /**
     * 获取是否图示
     *
     * @return C_ISFIG - 是否图示
     */
    public String getcIsfig() {
        return cIsfig;
    }

    /**
     * 设置是否图示
     *
     * @param cIsfig 是否图示
     */
    public void setcIsfig(String cIsfig) {
        this.cIsfig = cIsfig;
    }

    /**
     * 获取项目号
     *
     * @return C_ITEM - 项目号
     */
    public String getcItem() {
        return cItem;
    }

    /**
     * 设置项目号
     *
     * @param cItem 项目号
     */
    public void setcItem(String cItem) {
        this.cItem = cItem;
    }

    /**
     * 获取件号
     *
     * @return C_PARTNUMBER - 件号
     */
    public String getcPartnumber() {
        return cPartnumber;
    }

    /**
     * 设置件号
     *
     * @param cPartnumber 件号
     */
    public void setcPartnumber(String cPartnumber) {
        this.cPartnumber = cPartnumber;
    }

    /**
     * 获取层级
     *
     * @return C_TIER - 层级
     */
    public Integer getcTier() {
        return cTier;
    }

    /**
     * 设置层级
     *
     * @param cTier 层级
     */
    public void setcTier(Integer cTier) {
        this.cTier = cTier;
    }

    /**
     * 获取说明
     *
     * @return C_NOMENCLATURE - 说明
     */
    public String getcNomenclature() {
        return cNomenclature;
    }

    /**
     * 设置说明
     *
     * @param cNomenclature 说明
     */
    public void setcNomenclature(String cNomenclature) {
        this.cNomenclature = cNomenclature;
    }

    /**
     * 获取有效性代码
     *
     * @return C_EDDCODE - 有效性代码
     */
    public String getcEddcode() {
        return cEddcode;
    }

    /**
     * 设置有效性代码
     *
     * @param cEddcode 有效性代码
     */
    public void setcEddcode(String cEddcode) {
        this.cEddcode = cEddcode;
    }

    /**
     * 获取数量
     *
     * @return C_UPA - 数量
     */
    public String getcUpa() {
        return cUpa;
    }

    /**
     * 设置数量
     *
     * @param cUpa 数量
     */
    public void setcUpa(String cUpa) {
        this.cUpa = cUpa;
    }

    /**
     * 获取连接件
     *
     * @return C_CON - 连接件
     */
    public Integer getcCon() {
        return cCon;
    }

    /**
     * 设置连接件
     *
     * @param cCon 连接件
     */
    public void setcCon(Integer cCon) {
        this.cCon = cCon;
    }

    /**
     * 获取修改日期
     *
     * @return C_UPDATEDATE - 修改日期
     */
    public String getcUpdatedate() {
        return cUpdatedate;
    }

    /**
     * 设置修改日期
     *
     * @param cUpdatedate 修改日期
     */
    public void setcUpdatedate(String cUpdatedate) {
        this.cUpdatedate = cUpdatedate;
    }

    /**
     * 获取修改人
     *
     * @return C_UPDATEPERSON - 修改人
     */
    public String getcUpdateperson() {
        return cUpdateperson;
    }

    /**
     * 设置修改人
     *
     * @param cUpdateperson 修改人
     */
    public void setcUpdateperson(String cUpdateperson) {
        this.cUpdateperson = cUpdateperson;
    }
}