package com.example.earthquakeRadar.Database.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "project_part")
public class ProjectPartModel {

    @PrimaryKey(autoGenerate = true)
    int partId;

    String partName;
    int currentRowCount;
    int projectID;

    public ProjectPartModel(String partName, int currentRowCount, int projectID) {
        this.partName = partName;
        this.currentRowCount = currentRowCount;
        this.projectID = projectID;
    }

    @Override
    public String toString() {
        return "ProjectPartModel{" +
                "partId=" + partId +
                ", partName='" + partName + '\'' +
                ", currentRowCount=" + currentRowCount +
                ", projectID=" + projectID +
                '}';
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getCurrentRowCount() {
        return currentRowCount;
    }

    public void setCurrentRowCount(int currentRowCount) {
        this.currentRowCount = currentRowCount;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}
