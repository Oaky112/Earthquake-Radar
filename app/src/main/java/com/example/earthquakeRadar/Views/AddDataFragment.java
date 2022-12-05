package com.example.earthquakeRadar.Views;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.earthquakeRadar.Database.DAO.ProjectDataDao;
import com.example.earthquakeRadar.Database.Helper.DatabaseHelper;
import com.example.earthquakeRadar.Database.Models.ProjectDataModel;
import com.example.earthquakeRadar.R;
import com.example.earthquakeRadar.databinding.FragmentAddDataBinding;

import java.util.Objects;


public class AddDataFragment extends Fragment {

    FragmentAddDataBinding binding;
    String name = "";
    ProjectDataModel dataModel = null;
    private Activity context;

    public AddDataFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddDataBinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            dataModel = (ProjectDataModel) getArguments().getSerializable("projectObject");
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();

        if (dataModel != null) {
            binding.EQNameEdt.setText(dataModel.getProjectName());
            binding.magnitudeSizeEdt.setText(String.valueOf(dataModel.getHookSize()));
            binding.depthSizeEdt.setText(String.valueOf(dataModel.getNeedleSize()));
            binding.descOfWavesEdt.setText(dataModel.getYarnWeight());
            binding.eQDesEdt.setText(dataModel.getProjectDesc());
            Log.d("DATA","project ID is "+dataModel.getId());
        }

        binding.addInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.EQNameEdt.getText().toString();
                String hookSize = binding.magnitudeSizeEdt.getText().toString();
                String needleSize = binding.depthSizeEdt.getText().toString();
                String yarW = binding.descOfWavesEdt.getText().toString();
                String projectDes = binding.eQDesEdt.getText().toString();

                if (name.isEmpty() || hookSize.isEmpty() || needleSize.isEmpty() || yarW.isEmpty()) {
                    Toast.makeText(getActivity(), context.getResources().getString(R.string.valid_data), Toast.LENGTH_SHORT).show();
                    return;
                }

                int hookS = Integer.parseInt(hookSize);
                int needleS = Integer.parseInt(needleSize);

                ProjectDataModel projectDataModel = new ProjectDataModel(name, hookS, needleS, yarW, projectDes);
                ProjectDataDao projectDataDao = DatabaseHelper.getInstance(getActivity()).projectDataDao();


                if (dataModel == null) {
                    new InsertProjectAsyncTask(projectDataDao, context).execute(projectDataModel);
                } else {
                    projectDataModel.setId(dataModel.getId());
                    new UpdateProjectAsyncTask(projectDataDao, context).execute(projectDataModel);
                }
                Log.d("DATA", projectDataModel.toString());

            }
        });

        binding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new MainDashboardFragment(), "");
                transaction.commit();
                Objects.requireNonNull((((AppCompatActivity) context)).getSupportActionBar()).setTitle(context.getResources().getString(R.string.dashboard));

                //Toast.makeText(getActivity(), "cancel", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // we are creating a async task method to insert new course.
    private static class InsertProjectAsyncTask extends AsyncTask<ProjectDataModel, Void, Void> {
        Activity context;
        private ProjectDataDao projectDataDao;

        private InsertProjectAsyncTask(ProjectDataDao dao, Activity context) {
            this.projectDataDao = dao;
            this.context = context;
        }

        @Override
        protected Void doInBackground(ProjectDataModel... model) {
            // below line is use to insert our modal in dao.
            projectDataDao.insert(model[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Log.d("DATA", "inserted completed");
            FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, new MainDashboardFragment(), "");
            transaction.commit();
            Objects.requireNonNull((((AppCompatActivity) context)).getSupportActionBar()).setTitle(context.getResources().getString(R.string.dashboard));

            //Toast.makeText(geet, "", Toast.LENGTH_SHORT).show();
        }
    }

    // we are creating a async task method to update our course.
    // we are creating a async task method to insert new course.
    private static class UpdateProjectAsyncTask extends AsyncTask<ProjectDataModel, Void, Void> {
        Activity context;
        private ProjectDataDao projectDataDao;

        private UpdateProjectAsyncTask(ProjectDataDao dao, Activity context) {
            this.projectDataDao = dao;
            this.context = context;
        }

        @Override
        protected Void doInBackground(ProjectDataModel... model) {
            // below line is use to insert our modal in dao.
            projectDataDao.update(model[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Log.d("DATA", "updated completed");
            FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, new MainDashboardFragment(), "");
            transaction.commit();
            Objects.requireNonNull((((AppCompatActivity) context)).getSupportActionBar()).setTitle(context.getResources().getString(R.string.dashboard));

        }
    }
}