package com.example.earthquakeRadar.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthquakeRadar.Database.DAO.ProjectDataDao;
import com.example.earthquakeRadar.Database.DAO.ProjectPartDao;
import com.example.earthquakeRadar.Database.Helper.DatabaseHelper;
import com.example.earthquakeRadar.Database.Models.ProjectDataModel;
import com.example.earthquakeRadar.Database.Models.ProjectPartModel;
import com.example.earthquakeRadar.R;
import com.example.earthquakeRadar.Views.MainDashboardFragment;
import com.example.earthquakeRadar.databinding.ProjectPartItemBinding;

import java.util.List;
import java.util.Objects;

public class ProjectPartAdapter extends RecyclerView.Adapter<ProjectPartAdapter.ViewHolder> {

    Context context;
    List<ProjectPartModel> projectPartModelList;

    public ProjectPartAdapter(Context context, List<ProjectPartModel> projectPartModelList) {
        this.context = context;
        this.projectPartModelList = projectPartModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ProjectPartItemBinding projectPartItemBinding = ProjectPartItemBinding.inflate(LayoutInflater.from(context), parent, false);

        return new ViewHolder(projectPartItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProjectPartModel projectPartModel = projectPartModelList.get(position);
        final int[] count = {projectPartModel.getCurrentRowCount()};
        holder.projectPartItemBinding.counterTv.setText(String.valueOf(count[0]));
        holder.projectPartItemBinding.partNameTV.setText(projectPartModel.getPartName());

        holder.projectPartItemBinding.btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count[0]++;
                holder.projectPartItemBinding.counterTv.setText(String.valueOf(count[0]));
                projectPartModel.setCurrentRowCount(count[0]);
                new UpdateProjectPartAsyncTask(DatabaseHelper.getInstance(context).projectPartDao(),(Activity) context).execute(projectPartModel);
            }
        });
        holder.projectPartItemBinding.btnDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count[0] == 0){
                    return;
                }
                count[0]--;
                holder.projectPartItemBinding.counterTv.setText(String.valueOf(count[0]));
                projectPartModel.setCurrentRowCount(count[0]);
                new UpdateProjectPartAsyncTask(DatabaseHelper.getInstance(context).projectPartDao(),(Activity) context).execute(projectPartModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectPartModelList.size();
    }

    // we are creating a async task method to insert new course.
    private static class UpdateProjectPartAsyncTask extends AsyncTask<ProjectPartModel, Void, Void> {
        Activity context;
        private ProjectPartDao projectDataDao;

        private UpdateProjectPartAsyncTask(ProjectPartDao dao, Activity context) {
            this.projectDataDao = dao;
            this.context = context;
        }

        @Override
        protected Void doInBackground(ProjectPartModel... model) {
            // below line is use to insert our modal in dao.
            projectDataDao.update(model[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Log.d("DATA", "updated..... row........");
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ProjectPartItemBinding projectPartItemBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            projectPartItemBinding = ProjectPartItemBinding.bind(itemView);
        }

        public ProjectPartItemBinding getProjectPartItemBinding() {
            return projectPartItemBinding;
        }
    }
}
