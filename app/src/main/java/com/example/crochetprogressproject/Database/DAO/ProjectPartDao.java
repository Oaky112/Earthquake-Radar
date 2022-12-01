package com.example.crochetprogressproject.Database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.crochetprogressproject.Database.Models.ProjectPartModel;

import java.util.List;

@androidx.room.Dao
public interface ProjectPartDao {

    @Insert
    void insert(ProjectPartModel model);

    @Update
    void update(ProjectPartModel model);

    @Delete
    void delete(ProjectPartModel model);


    @Query("DELETE FROM project_part")
    void deleteAllProjectPart();

    @Query("DELETE FROM project_part WHERE projectID = :id")
    void deleteProjectPartByProjectID(int id);

    @Query("SELECT * FROM project_part")
    LiveData<List<ProjectPartModel>> getAllProjectParts();

    @Query("SELECT * FROM project_part WHERE projectID = :id")
    LiveData<List<ProjectPartModel>> getAllLiveDataProjectPartsByProjectID(int id);

    @Query("SELECT * FROM project_part WHERE projectID = :id ORDER BY partId")
    List<ProjectPartModel> getAllProjectPartsByProjectID(int id);
}
