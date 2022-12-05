package com.example.earthquakeRadar.Adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthquakeRadar.Database.DAO.ProjectDataDao;
import com.example.earthquakeRadar.Database.Helper.DatabaseHelper;
import com.example.earthquakeRadar.Database.Models.ProjectDataModel;
import com.example.earthquakeRadar.R;
import com.example.earthquakeRadar.Views.AddDataFragment;
import com.example.earthquakeRadar.Views.ProjectDetailsFragment;
import com.example.earthquakeRadar.databinding.ProjectDataItemBinding;

import java.util.List;
import java.util.Objects;

public class ProjectDataAdapter extends RecyclerView.Adapter<ProjectDataAdapter.ViewHolder> {

    Context context;
    List<ProjectDataModel> projectDataModelList;


    public ProjectDataAdapter(Context context, List<ProjectDataModel> projectDataModelList) {
        this.context = context;
        this.projectDataModelList = projectDataModelList;
    }

    @NonNull
    @Override
    public ProjectDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ProjectDataItemBinding userItemBinding = ProjectDataItemBinding.inflate(LayoutInflater.from(context), parent, false);

        return new ViewHolder(userItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectDataAdapter.ViewHolder holder, int position) {

        ProjectDataModel projectDataModel = projectDataModelList.get(position);
        holder.getUserItemBinding().projectName.setText(projectDataModel.getProjectName());
        String hookSize = String.valueOf(projectDataModel.getHookSize());
        holder.getUserItemBinding().hookSize.setText(hookSize);

        holder.getUserItemBinding().deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteProjectAsyncTask(DatabaseHelper.getInstance(context).projectDataDao()).execute(projectDataModel);
            }
        });

        holder.getUserItemBinding().editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putSerializable("projectObject",projectDataModel);
                AddDataFragment fragment = new  AddDataFragment();
                fragment.setArguments(args);

                FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.content, fragment, "");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Objects.requireNonNull((((AppCompatActivity) context)).getSupportActionBar()).setTitle(context.getResources().getString(R.string.edit_project));

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "clicked "+position, Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();
                args.putSerializable("projectObject",projectDataModel);
                ProjectDetailsFragment fragment = new ProjectDetailsFragment();
                fragment.setArguments(args);

                FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.content, fragment, "");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Objects.requireNonNull((((AppCompatActivity) context)).getSupportActionBar()).setTitle(context.getResources().getString(R.string.project_details));
            }
        });

    }

    @Override
    public int getItemCount() {
        return projectDataModelList.size();
    }

    // we are creating a async task method to delete course.
    private static class DeleteProjectAsyncTask extends AsyncTask<ProjectDataModel, Void, Void> {
        private ProjectDataDao dao;

        private DeleteProjectAsyncTask(ProjectDataDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ProjectDataModel... models) {
            // below line is use to delete
            // our course modal in dao.
            dao.delete(models[0]);
            return null;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ProjectDataItemBinding userItemBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userItemBinding = ProjectDataItemBinding.bind(itemView);
        }

        public ProjectDataItemBinding getUserItemBinding() {
            return userItemBinding;
        }
    }
}
