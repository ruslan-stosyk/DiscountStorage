package com.stosh.discountstorage.Auth;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.stosh.discountstorage.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private final int SING_UP = 2;
    private final int RESET = 3;

    private View view;
    private Button btnLogin, btnSingUpFrag, btnResetPass;
    private EditText editTextEmail, editTextPassword;
    private View.OnClickListener clickListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);


        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnLogin:
                        String email = editTextEmail.getText().toString();
                        String password = editTextPassword.getText().toString();
                        if (TextUtils.isEmpty(email)) {
                            editTextEmail.setError(getString(R.string.email_is_empty));
                            break;
                        } else if (TextUtils.isEmpty(password)) {
                            editTextPassword.setError(getString(R.string.password_is_empty));
                            break;
                        } else if (password.length() < 6) {
                            editTextPassword.setError(getString(R.string.password_to_short));
                            break;
                        }
                        listener.onLogin(email, password);

                        break;

                    case R.id.btnSingUpFrag:
                        listener.onClickBtn(SING_UP);
                        break;

                    case R.id.btnResetFrag:
                        listener.onClickBtn(RESET);
                        onDestroy();
                        break;
                }
            }
        };
        init();
        return view;
    }

    private void init() {
        editTextEmail = (EditText) view.findViewById(R.id.editTextEmailLogin);
        editTextPassword = (EditText) view.findViewById(R.id.editTextPasswordLogin);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnSingUpFrag = (Button) view.findViewById(R.id.btnSingUpFrag);
        btnResetPass = (Button) view.findViewById(R.id.btnResetFrag);

        btnLogin.setOnClickListener(clickListener);
        btnSingUpFrag.setOnClickListener(clickListener);
        btnResetPass.setOnClickListener(clickListener);
    }

    public interface OnClickBtnListener {
        public void onClickBtn(int code);
        public void onLogin(String email, String password);
    }

    private OnClickBtnListener listener;

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        try {
            listener = (OnClickBtnListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implements OnClicBtnListener");
        }
    }
}
