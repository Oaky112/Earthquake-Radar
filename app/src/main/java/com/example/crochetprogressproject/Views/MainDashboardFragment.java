package com.example.crochetprogressproject.Views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.crochetprogressproject.Adapters.ProjectDataAdapter;
import com.example.crochetprogressproject.Database.Helper.DatabaseHelper;
import com.example.crochetprogressproject.Database.Models.ProjectDataModel;
import com.example.crochetprogressproject.R;
import com.example.crochetprogressproject.databinding.FragmentMainDashboardBinding;

import java.util.List;
import java.util.Objects;

public class MainDashboardFragment extends Fragment {


    FragmentMainDashboardBinding binding;
    ProjectDataAdapter projectDataAdapter;
    LiveData<List<ProjectDataModel>> listLiveData;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainDashboardBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.projectDataRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.projectDataRecyclerView.setHasFixedSize(true);

        listLiveData = DatabaseHelper.getInstance(getActivity()).projectDataDao().getAllProjectData();
        listLiveData.observe(getActivity(), new Observer<List<ProjectDataModel>>() {
            @Override
            public void onChanged(List<ProjectDataModel> projectDataModels) {
                projectDataAdapter = new ProjectDataAdapter(getActivity(), projectDataModels);
                binding.projectDataRecyclerView.setAdapter(projectDataAdapter);
                if (projectDataModels.isEmpty()) {
                    binding.noProjectTv.setVisibility(View.VISIBLE);
                } else {
                    binding.noProjectTv.setVisibility(View.INVISIBLE);
                }
            }
        });


        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "CLICKED", Toast.LENGTH_SHORT).show();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.content, new AddDataFragment(), "");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(getActivity().getResources().getString(R.string.add_project));
            }
        });
    }
}