package com.example.pressurepoints;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import DAO.UsersDAO;
import DTO.UsersDTO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewRegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewRegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewRegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewRegisterFragment newInstance(String param1, String param2) {
        NewRegisterFragment fragment = new NewRegisterFragment();
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
        ((MainActivity)getActivity()).setTitle("Cadastre-se");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_register, container, false);

        ImageButton btnBack = (ImageButton) view.findViewById(R.id.bt_back);
        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Navigation.findNavController(v).navigate(R.id.action_newRegisterFragment_to_loginFragment);
            }
        });

        Button btnSave = (Button) view.findViewById(R.id.bt_submit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userName = view.findViewById(R.id.et_userNameRegister);
                EditText userGenreRegister = view.findViewById(R.id.et_userGenreRegister);
                EditText userPasswordRegister = view.findViewById(R.id.et_userPasswordRegister);
                EditText userConfirmPasswordRegister = view.findViewById(R.id.et_userConfirmPasswordRegister);
                EditText userChronicDiseaseObsRegister = view.findViewById(R.id.et_userChronicDiseaseObsRegister);
                EditText userAgeRegister = view.findViewById(R.id.et_userAgeRegister);
                EditText userHeightRegister = view.findViewById(R.id.et_userHeightRegister);
                EditText userWeightRegister = view.findViewById(R.id.et_userWeightRegister);

                String requiredField = UsersDTO.checkRequiredFields(userName.getText().toString().trim(), userGenreRegister.getText().toString().charAt(0),
                        userPasswordRegister.getText().toString().trim(), userConfirmPasswordRegister.getText().toString().trim(),
                        userChronicDiseaseObsRegister.getText().toString().trim(), Integer.parseInt(userAgeRegister.getText().toString().trim()),
                        userHeightRegister.getText().toString().trim(), userWeightRegister.getText().toString().trim());
                if (requiredField.isEmpty())
                {
                    if(userPasswordRegister.getText().toString().equals(userConfirmPasswordRegister.getText().toString())){
                        UsersDTO usersDTO = new UsersDTO(userName.getText().toString().trim(),
                                userPasswordRegister.getText().toString().trim(),
                                userConfirmPasswordRegister.getText().toString().trim(),
                                userGenreRegister.getText().toString().charAt(0),
                                Integer.parseInt(userAgeRegister.getText().toString().trim()),
                                userHeightRegister.getText().toString().trim().replace(",","."),
                                userWeightRegister.getText().toString().trim().replace(",","."),
                                userChronicDiseaseObsRegister.getText().toString().trim());
                        UsersDAO usersDAO = new UsersDAO();
                        usersDAO.insertUserDTO(usersDTO);
                        Navigation.findNavController(v).navigate(R.id.action_newRegisterFragment_to_loginFragment);
                        if(usersDAO.isSucess()){
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Cadatro efetuado com sucesso.", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(),
                                "A campo senha e confirmação de senha estão divergentes!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Por favor verifique os valores dos campos:\n" + requiredField, Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}