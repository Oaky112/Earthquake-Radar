package com.example.earthquakeRadar.Database.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "projectData")
public class ProjectDataModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;

    String projectName;
    int hookSize;
    int needleSize;
    String yarnWeight;
    String projectDesc;

    public ProjectDataModel( String projectName, int hookSize, int needleSize, String yarnWeight, String projectDesc) {
        this.projectName = projectName;
        this.hookSize = hookSize;
        this.needleSize = needleSize;
        this.yarnWeight = yarnWeight;
        this.projectDesc = projectDesc;
    }

    @Override
    public String toString() {
        return "ProjectDataModel{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", hookSize=" + hookSize +
                ", needleSize=" + needleSize +
                ", yarnWeight='" + yarnWeight + '\'' +
                ", projectDesc='" + projectDesc + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getHookSize() {
        return hookSize;
    }

    public void setHookSize(int hookSize) {
        this.hookSize = hookSize;
    }

    public int getNeedleSize() {
        return needleSize;
    }

    public void setNeedleSize(int needleSize) {
        this.needleSize = needleSize;
    }

    public String getYarnWeight() {
        return yarnWeight;
    }

    public void setYarnWeight(String yarnWeight) {
        this.yarnWeight = yarnWeight;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }
}
