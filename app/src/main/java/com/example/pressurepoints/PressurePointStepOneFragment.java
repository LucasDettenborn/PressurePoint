package com.example.pressurepoints;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PressurePointStepOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PressurePointStepOneFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PressurePointStepOneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PressurePointStepOneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PressurePointStepOneFragment newInstance(String param1, String param2) {
        PressurePointStepOneFragment fragment = new PressurePointStepOneFragment();
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
        ((MainActivity)getActivity()).setTitle("Orientações iniciais");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pressure_point_step_one, container, false);

        YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        ImageButton btnBack = (ImageButton) view.findViewById(R.id.bt_back);
        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Navigation.findNavController(v).navigate(R.id.action_pressurePointStepOneFragment_to_homeFragment);
            }
        });

        ImageButton btnNext = (ImageButton) view.findViewById(R.id.bt_next);
        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText levelBeforePressurePoints = view.findViewById(R.id.et_levelBeforePressurePoints);
                if(levelBeforePressurePoints != null && !levelBeforePressurePoints.getText().toString().trim().isEmpty() && Integer.parseInt(levelBeforePressurePoints.getText().toString().trim()) >= 0 && Integer.parseInt(levelBeforePressurePoints.getText().toString().trim()) <= 10){
                    ((MainActivity)getActivity()).getAnswersDataFlowControl().getAnswersForFlowDTO().setLevelBeforePressurePoints(Integer.parseInt(levelBeforePressurePoints.getText().toString().trim()));
                    Navigation.findNavController(v).navigate(R.id.action_pressurePointStepOneFragment_to_pressurePointStepTwoFragment);
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Por favor, informe um número válido para o campo, deve ser informado entre 0 e 10.", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}