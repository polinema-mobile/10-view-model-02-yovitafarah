package id.putraprima.mvvmlogin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.putraprima.mvvmlogin.R;
import id.putraprima.mvvmlogin.databinding.FragmentLoginBinding;
import id.putraprima.mvvmlogin.models.User;
import id.putraprima.mvvmlogin.viewmodels.LoginViewModel;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding fragmentLoginBinding;
    private LoginViewModel loginViewModel;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentLoginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        fragmentLoginBinding.setViewModel(loginViewModel);
        fragmentLoginBinding.setLifecycleOwner(this);
        return fragmentLoginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentLoginBinding.getViewModel().isLoggedIn().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean!=null){
                    if(aBoolean){
                        NavDirections action =
                                LoginFragmentDirections.actionLoginFragmentToHomeFragment(new User(fragmentLoginBinding.editTextEmail.getText().toString(),fragmentLoginBinding.editTextPassword.getText().toString()));
                        Navigation.findNavController(view).navigate(action);
                        loginViewModel.onLoginNavigated();
                    }
                }
            }
        });
    }
}