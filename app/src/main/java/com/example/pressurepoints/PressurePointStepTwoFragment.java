package com.example.pressurepoints;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Date;

import DAO.AnswersFlowDAO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PressurePointStepTwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PressurePointStepTwoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PressurePointStepTwoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PressurePointStepTwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PressurePointStepTwoFragment newInstance(String param1, String param2) {
        PressurePointStepTwoFragment fragment = new PressurePointStepTwoFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setTitle("Faça você mesmo");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pressure_point_step_two, container, false);

        YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        ImageButton btnBack = (ImageButton) view.findViewById(R.id.bt_back);
        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Navigation.findNavController(v).navigate(R.id.action_pressurePointStepTwoFragment_to_pressurePointStepOneFragment);
            }
        });

        Button btnNext = (Button) view.findViewById(R.id.bt_next);
        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v)
            {
                EditText levelAfterPressurePoints = view.findViewById(R.id.et_levelAfterPressurePoints);
                if(levelAfterPressurePoints != null && !levelAfterPressurePoints.getText().toString().trim().isEmpty() && Integer.parseInt(levelAfterPressurePoints.getText().toString().trim()) >= 0 && Integer.parseInt(levelAfterPressurePoints.getText().toString().trim()) <= 10){
                    //Confirm the correctly date convert
                    Date dNow = new Date();
                    try {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        dNow = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dtf.format(now));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    ((MainActivity)getActivity()).getAnswersDataFlowControl().getAnswersForFlowDTO().setUsers_Id(((MainActivity)getActivity()).getUsersControl().getCurrentUserData().getId());
                    if(dNow != null)
                        ((MainActivity) getActivity()).getAnswersDataFlowControl().getAnswersForFlowDTO().setAnswersDate(new java.sql.Date(dNow.getTime()));
                    ((MainActivity)getActivity()).getAnswersDataFlowControl().getAnswersForFlowDTO().setLevelAfterPressurePoints(Integer.parseInt(levelAfterPressurePoints.getText().toString().trim()));
                    ((MainActivity)getActivity()).getAnswersDataFlowControl().getAnswersForFlowDTO().setGotBetterAfter(AnswersDataFlowControl.getAnswersForFlowDTO().getLevelBeforePressurePoints() > Integer.parseInt(levelAfterPressurePoints.getText().toString().trim()) ? true : false);
                    //call save method
                    AnswersFlowDAO answersFlowDAO = new AnswersFlowDAO();
                    answersFlowDAO.insertAnswersFlowDTO(((MainActivity)getActivity()).getAnswersDataFlowControl().getAnswersForFlowDTO());
                    if(answersFlowDAO.isSucess()){
                        Navigation.findNavController(v).navigate(R.id.action_pressurePointStepTwoFragment_to_homeFragment);
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Interação gravada com sucesso!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Ocorreu um problema durante o processo de gravar sua informação na base de dados, por favor refaça o processo", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Por favor, informe um número válido para o campo, deve ser informado entre 0 e 10.", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}