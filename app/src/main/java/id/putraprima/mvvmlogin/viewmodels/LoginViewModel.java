package id.putraprima.mvvmlogin.viewmodels;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

import id.putraprima.mvvmlogin.models.User;

public class LoginViewModel extends ViewModel {
    public ObservableField<String> mUsername = new ObservableField<>();
    public ObservableField<String> mPassword = new ObservableField<>();
    private User user = new User();
    private MutableLiveData<Boolean> isLoggedIn = new MutableLiveData<>();


    public void validateLogin(){
        this.
        isLoggedIn.setValue(this.user.checkLogin(Objects.requireNonNull(mUsername.get()), mPassword.get()));
    }

    public LiveData<Boolean> isLoggedIn(){
        return isLoggedIn;
    }

    public void onLoginNavigated(){
        this.isLoggedIn.setValue(null);
    }

}
