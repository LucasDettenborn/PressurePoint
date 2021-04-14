package com.example.pressurepoints;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DAO.UsersDAO;
import DTO.UsersDTO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        ((MainActivity)getActivity()).setTitle("Digito Ajuda");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button btnNewAccount = (Button) view.findViewById(R.id.bt_newRegister);
        btnNewAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_newRegisterFragment);
            }
        });
        Button btnLogin = (Button) view.findViewById(R.id.bt_submit);
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText etUserName = (EditText) view.findViewById(R.id.et_username);
                EditText etPassword = (EditText) view.findViewById(R.id.et_password);
                if(etUserName != null && etUserName.getText().toString().trim().length() > 0 && etPassword != null && etPassword.getText().toString().trim().length() > 0){
                    if(etUserName.getText().toString().equals("admin") && etPassword.getText().toString().equals("admin")){
                        //Temp. adjust
                        ((MainActivity)getActivity()).getUsersControl().setAdmUser(false);
                        ((MainActivity)getActivity()).getUsersControl().setCurrentUserData(null);
                    }
                    else{
                        UsersDAO uDAO = new UsersDAO();
                        UsersDTO usersDTO = uDAO.selectUserDTO(etUserName.getText().toString(), etPassword.getText().toString());
                        if (usersDTO != null && uDAO.isSucess()) {
                            ((MainActivity)getActivity()).getUsersControl().setAdmUser(false);
                            ((MainActivity)getActivity()).getUsersControl().setCurrentUserData(usersDTO);
                            Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_homeFragment);
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Erro quando acessado o banco ou usuário inexistente!", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "O campo nome de usuário ou senha se encontra vazio!", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}