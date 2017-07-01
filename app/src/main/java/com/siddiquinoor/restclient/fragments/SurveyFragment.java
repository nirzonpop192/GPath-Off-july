package com.siddiquinoor.restclient.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.data_model.SurveyModel;
import com.siddiquinoor.restclient.views.adapters.SurveyAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SurveyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SurveyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SURVEY_MODEL = "survey_model";

    private RecyclerView rvSurvey;

    private Context context;
    private SurveyAdapter surveyAdapter;

    // TODO: Rename and change types of parameters
    private SurveyModel surveyModel;


    public SurveyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param surveyModel Parameter 2.
     * @return A new instance of fragment SurveyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SurveyFragment newInstance(SurveyModel surveyModel) {
        SurveyFragment fragment = new SurveyFragment();
        Bundle args = new Bundle();
        args.putParcelable(SURVEY_MODEL, surveyModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            surveyModel = getArguments().getParcelable(SURVEY_MODEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_survey, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();

        rvSurvey = (RecyclerView) view.findViewById(R.id.rvSurvey);
        rvSurvey.setHasFixedSize(true);
        LinearLayoutManager  llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvSurvey.setLayoutManager(llm);
        surveyAdapter = new SurveyAdapter(surveyModel.getDtSurveyTableDataModels(),context);
        rvSurvey.setAdapter(surveyAdapter);

    }
}
