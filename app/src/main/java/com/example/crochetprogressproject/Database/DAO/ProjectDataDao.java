package com.example.crochetprogressproject.Database.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.crochetprogressproject.Database.Models.ProjectDataModel;

import java.util.List;

@Dao
public interface ProjectDataDao {


    @Insert
    void insert(ProjectDataModel projectDataModel);

    @Update
    void update(ProjectDataModel model);

    @Delete
    void delete(ProjectDataModel model);


    @Query("DELETE FROM projectData")
    void deleteAllCourses();

    @Query("SELECT * FROM projectData")
    LiveData<List<ProjectDataModel>> getAllProjectData();

}
