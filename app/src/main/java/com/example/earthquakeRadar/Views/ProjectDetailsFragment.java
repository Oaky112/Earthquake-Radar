package com.example.earthquakeRadar.Views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.earthquakeRadar.Adapters.ProjectPartAdapter;
import com.example.earthquakeRadar.Database.DAO.ProjectDataDao;
import com.example.earthquakeRadar.Database.DAO.ProjectPartDao;
import com.example.earthquakeRadar.Database.Helper.DatabaseHelper;
import com.example.earthquakeRadar.Database.Models.ProjectDataModel;
import com.example.earthquakeRadar.Database.Models.ProjectPartModel;
import com.example.earthquakeRadar.R;
import com.example.earthquakeRadar.databinding.FragmentProjectDetailsBinding;

import java.util.List;

public class ProjectDetailsFragment extends Fragment {

    ProjectDataModel dataModel = null;
    FragmentProjectDetailsBinding binding;
    List<ProjectPartModel> projectPartModelList;
    ProjectPartAdapter projectPartAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getArguments() != null) {
            dataModel = (ProjectDataModel) getArguments().getSerializable("projectObject");
        }

        // Inflate the layout for this fragment
        binding = FragmentProjectDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (dataModel != null) {
            binding.projectNameTv.setText(dataModel.getProjectName());
            binding.hookSizeTv.setText(getActivity().getResources().getString(R.string.hook_size)+" : " + String.valueOf(dataModel.getHookSize()));
            binding.needleSizeTv.setText(getActivity().getResources().getString(R.string.needle_size)+" : " + String.valueOf(dataModel.getNeedleSize()));
            binding.ywTv.setText(getActivity().getResources().getString(R.string.yarn_weight)+" : " + dataModel.getYarnWeight());
            binding.projectDescTv.setText(dataModel.getProjectDesc());
        }

        projectPartModelList = DatabaseHelper.getInstance(getActivity()).projectPartDao().getAllProjectPartsByProjectID(dataModel.getId());
        projectPartAdapter = new ProjectPartAdapter(getActivity(), projectPartModelList);

        binding.AddNewPartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editTextField = new EditText(getActivity());
                editTextField.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                editTextField.setHint(getActivity().getResources().getString(R.string.enter_part_name));
                LinearLayout linearLayout = new LinearLayout(getActivity());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                layoutParams.setMargins(40, 10, 40, 0);
                linearLayout.addView(editTextField, layoutParams);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(getActivity().getResources().getString(R.string.enter_part_name));
                builder.setView(linearLayout);
                builder.setPositiveButton(getActivity().getResources().getString(R.string.add_btn), (dialog, which) -> {

                });
                builder.setNegativeButton(getActivity().getResources().getString(R.string.cancel), (dialog, which) -> {
                    dialog.cancel();

                });

                AlertDialog dialog = builder.create();
                dialog.show();

                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editTextField.getText().toString().isEmpty()) {
                            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.valid_data), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        dialog.cancel();
                        ProjectPartModel projectPartModel = new ProjectPartModel(editTextField.getText().toString(), 0, dataModel.getId());
                        new InsertProjectPartAsyncTask(DatabaseHelper.getInstance(getActivity()).projectPartDao(), getActivity()).execute(projectPartModel);

                    }
                });


            }
        });

        binding.partRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.partRecyclerView.setHasFixedSize(true);
        binding.partRecyclerView.setAdapter(projectPartAdapter);

    }

    // we are creating a async task method to insert new course.
    private class InsertProjectPartAsyncTask extends AsyncTask<ProjectPartModel, Void, Void> {
        Activity context;
        private ProjectPartDao projectDataDao;

        private InsertProjectPartAsyncTask(ProjectPartDao dao, Activity context) {
            this.projectDataDao = dao;
            this.context = context;
        }

        @Override
        protected Void doInBackground(ProjectPartModel... model) {
            // below line is use to insert our modal in dao.
            projectDataDao.insert(model[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Log.d("DATA", "inserted completed");

            Toast.makeText(context, "Added Part", Toast.LENGTH_SHORT).show();
            projectPartModelList = DatabaseHelper.getInstance(getActivity()).projectPartDao().getAllProjectPartsByProjectID(dataModel.getId());
            projectPartAdapter = new ProjectPartAdapter(context, projectPartModelList);
            binding.partRecyclerView.setAdapter(projectPartAdapter);
            projectPartAdapter.notifyDataSetChanged();
           /* FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, new MainDashboardFragment(), "");
            transaction.commit();
            Objects.requireNonNull(((AppCompatActivity) context).getSupportActionBar()).setTitle("Dashboard");*/
            //Toast.makeText(geet, "", Toast.LENGTH_SHORT).show();
        }
    }

}