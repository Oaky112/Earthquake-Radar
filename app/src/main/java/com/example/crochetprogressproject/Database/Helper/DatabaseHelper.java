package com.example.crochetprogressproject.Database.Helper;

import android.content.Context;
import android.support.annotation.NonNull;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.crochetprogressproject.Database.DAO.ProjectDataDao;
import com.example.crochetprogressproject.Database.DAO.ProjectPartDao;
import com.example.crochetprogressproject.Database.Models.ProjectDataModel;
import com.example.crochetprogressproject.Database.Models.ProjectPartModel;

@Database(entities = {ProjectDataModel.class, ProjectPartModel.class}, version = 2)
public abstract class DatabaseHelper extends RoomDatabase {

    public static final String DATABASE_NAME = "project_database";
    // below line is to create a callback for our room database.
    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // this method is called when database is created
            // and below line is to populate our data.
            // new PopulateDbAsyncTask(instance).execute();
        }
    };
    // below line is to create instance
    // for our database class.
    private static DatabaseHelper instance;

    // on below line we are getting instance for our database.
    public static synchronized DatabaseHelper getInstance(Context context) {
        // below line is to check if
        // the instance is null or not.
        if (instance == null) {
            // if the instance is null we
            // are creating a new instance
            instance =
                    // for creating a instance for our database
                    // we are creating a database builder and passing
                    // our database class with our database name.
                    Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseHelper.class, DATABASE_NAME)
                            // below line is use to add fall back to
                            // destructive migration to our database.
                            .fallbackToDestructiveMigration()
                            // below line is to add callback
                            // to our database.
                            .addCallback(roomCallback)
                            .allowMainThreadQueries()
                            // below line is to
                            // build our database.
                            .build();
        }
        // after creating an instance
        // we are returning our instance
        return instance;
    }

    // below line is to create
    // abstract variable for dao.
    public abstract ProjectDataDao projectDataDao();

    // abstract variable for dao.
    public abstract ProjectPartDao projectPartDao();
}
