package com.yotop.aipc.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "constants")
@PropertySource("classpath:constants.properties")
public class Constants {


    private String exportPicturePath;
    private String uploadPath;
    private String sessionCurrentUser;
    private String templetFilePath;
    private String dataswitch;
    private String wordFilePath;
    //证照和签证生成的xml
    private String exportfmpath;

    //excel 下載地址
    private String excelpath;

    private String empInfo;

    private String uploadForm;

    private String zipDownFile;

    private String exportExcelPath;

    public String getExportPicturePath() {
        return exportPicturePath;
    }

    public void setExportPicturePath(String exportPicturePath) {
        this.exportPicturePath = exportPicturePath;
    }

    public String getZipDownFile() {
        return zipDownFile;
    }

    public void setZipDownFile(String zipDownFile) {
        this.zipDownFile = zipDownFile;
    }

    public String getUploadForm() {
        return uploadForm;
    }

    public void setUploadForm(String uploadForm) {
        this.uploadForm = uploadForm;
    }

    public void setUploadSystem(String uploadSystem) {
        this.uploadSystem = uploadSystem;
    }

    public void setUploadeefAcademy(String uploadeefAcademy) {
        this.uploadeefAcademy = uploadeefAcademy;
    }

    public String getUploadSystem() {
        return uploadSystem;
    }

    public String getUploadeefAcademy() {
        return uploadeefAcademy;
    }

    private String uploadSystem;

    private String uploadeefAcademy;
    private String uploadjyyAcademy;
    private String picturePath;

    public void setUploadjyyAcademy(String uploadeyyAcademy) {
        this.uploadjyyAcademy = uploadeyyAcademy;
    }

    public String getUploadjyyAcademy() {
        return uploadjyyAcademy;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getPicturePath() {

        return picturePath;
    }

    public String getEmpInfo() {
        return empInfo;
    }

    public void setEmpInfo(String empInfo) {
        this.empInfo = empInfo;
    }

    public String getExportfmpath() {
        return exportfmpath;
    }

    public void setExportfmpath(String exportfmpath) {
        this.exportfmpath = exportfmpath;
    }

    public String getWordFilePath() {
		return wordFilePath;
	}

	public void setWordFilePath(String wordFilePath) {
		this.wordFilePath = wordFilePath;
	}

    public String getExcelpath() {
        return excelpath;
    }

    public void setExcelpath(String excelpath) {
        this.excelpath = excelpath;
    }

    public String getDataswitch() {
		return dataswitch;
	}

	public void setDataswitch(String dataswitch) {
		this.dataswitch = dataswitch;
	}

	public String getTempletFilePath() {
        return templetFilePath;
    }

    public void setTempletFilePath(String templetFilePath) {
        this.templetFilePath = templetFilePath;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getSessionCurrentUser() {
        return sessionCurrentUser;
    }

    public void setSessionCurrentUser(String sessionCurrentUser) {
        this.sessionCurrentUser = sessionCurrentUser;
    }

    public String getExportExcelPath() {
        return exportExcelPath;
    }

    public void setExportExcelPath(String exportExcelPath) {
        this.exportExcelPath = exportExcelPath;
    }
}
