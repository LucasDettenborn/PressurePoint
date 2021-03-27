package com.example.pressurepoints;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import DAO.AnswersFlowDAO;
import DTO.AnswersFlowDTO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportFragment newInstance(String param1, String param2) {
        ReportFragment fragment = new ReportFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setTitle("Relatório");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        ImageButton btnBackToHomeScreen = (ImageButton) view.findViewById(R.id.bt_backToHomeScreen);
        btnBackToHomeScreen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Navigation.findNavController(v).navigate(R.id.action_reportFragment_to_homeFragment);
            }
        });

        ListView listView = view.findViewById(R.id.listViewReport);

        // get all user iteration from AnswersFlowDTO
        AnswersFlowDAO answersFlowDAO = new AnswersFlowDAO();
        ArrayList<AnswersFlowDTO> answersFlowDTOList = answersFlowDAO.getAnswersFlowDTOList(((MainActivity) getActivity()).getUsersControl().getCurrentUserData().getId());
        String rAnswersDate[] = null;
        String rLevelBeforePressurePoints[] = null;
        String rLevelAfterPressurePoints[] = null;
        int position = 0;
        if(answersFlowDTOList != null && !answersFlowDTOList.isEmpty()){
            for (AnswersFlowDTO answersFlowItem : answersFlowDTOList) {
                if(position == 0){
                    rAnswersDate = new String[answersFlowDTOList.size()];
                    rLevelBeforePressurePoints = new String[answersFlowDTOList.size()];
                    rLevelAfterPressurePoints = new String[answersFlowDTOList.size()];
                }
                rAnswersDate[position] = answersFlowItem.getAnswersDateToShow();
                rLevelBeforePressurePoints[position] = String.valueOf(answersFlowItem.getLevelBeforePressurePoints());
                rLevelAfterPressurePoints[position] =  String.valueOf(answersFlowItem.getLevelAfterPressurePoints());
                position++;
            }
            if(rAnswersDate != null && rLevelBeforePressurePoints != null && rLevelAfterPressurePoints != null){
                ListViewItemAdapter listViewItemAdapter = new ListViewItemAdapter(getContext(), rAnswersDate, rLevelBeforePressurePoints, rLevelAfterPressurePoints);
                listView.setAdapter(listViewItemAdapter);
            }
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;
    }

    class ListViewItemAdapter extends ArrayAdapter<String>{
        Context context;
        String rAnswersDate[];
        String rLevelBeforePressurePoints[];
        String rLevelAfterPressurePoints[];

        ListViewItemAdapter(Context c, String answersDate[], String levelBeforePressurePoints[], String levelAfterPressurePoints[]){
            super(c, R.layout.report_item, R.id.txtAnswersDate, answersDate);
            this.context = c;
            this.rAnswersDate = answersDate;
            this.rLevelBeforePressurePoints = levelBeforePressurePoints;
            this.rLevelAfterPressurePoints = levelAfterPressurePoints;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View report_item = layoutInflater.inflate(R.layout.report_item, parent, false);

            TextView answersDate = report_item.findViewById(R.id.txtAnswersDate);
            TextView levelBeforePressurePoints = report_item.findViewById(R.id.txtLevelBeforePressurePoints);
            TextView levelAfterPressurePoints = report_item.findViewById(R.id.txtLevelAfterPressurePoints);

            answersDate.setText(rAnswersDate[position]);
            levelBeforePressurePoints.setText(getString(R.string.scoreOrientationPhaseForReport) + " " + rLevelBeforePressurePoints[position] + " " + getString(R.string.unitOfMeasurementPoints));
            levelAfterPressurePoints.setText(getString(R.string.scoreOrientationPracticeForReport) + " " + rLevelAfterPressurePoints[position] + " " + getString(R.string.unitOfMeasurementPoints));

            return report_item;
        }

    }
}