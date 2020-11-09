package id.putraprima.mvvmlogin.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.putraprima.mvvmlogin.models.LoggedIn;

public class LoginViewModel extends ViewModel {
    private String email = "yovitafarahdela@gmail.com";
    private String password = "yovita19";
    private MutableLiveData<LoggedIn> loggedInMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loggedMutableLive = new MutableLiveData<>();
    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();
    private LoggedIn loggedIn;

    private MutableLiveData<LoggedIn> userMutableLiveData;
    public LoginViewModel(LoggedIn loggedIn) {
        this.loggedIn = loggedIn;
        this.loggedInMutableLiveData.setValue(this.loggedIn);
    }
    public MutableLiveData<LoggedIn> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }
    public void doLogin() {
        Log.d("Email",loggedIn.email);
        Log.d("Pass",loggedIn.password);
        loggedMutableLive.setValue(false);

        if (loggedIn.email.equals(email) && loggedIn.password.equals(password)) {
            loggedInMutableLiveData.setValue(loggedIn);
            loggedMutableLive.setValue(true);
            return;
        }else if(loggedIn.email.isEmpty() || loggedIn.email == null && loggedIn.password.equals(password)){
            EmailAddress.setValue("Enter an E-Mail Address");
            loggedMutableLive.setValue(false);
            return;
        } else if (loggedIn.email.isEmpty() && loggedIn.password.isEmpty()){
            EmailAddress.setValue("Masukkan alamat email Anda"); // set pesan
            Password.setValue("Masukkan password Anda"); // set pesan
            loggedMutableLive.setValue(false);
            return;
        }else if (!loggedIn.isEmailValid()) {
            EmailAddress.setValue("Enter a Valid E-mail Address");
            loggedMutableLive.setValue(true);
            return;
        } else if (loggedIn.email.equals(email) && loggedIn.password.isEmpty() || loggedIn.password == null || !loggedIn.password.equals(password)) {
            Password.setValue("Enter a Password");
            loggedMutableLive.setValue(false);
            return;
        } else if (!loggedIn.isPasswordLengthGreaterThan5()) {
            Password.setValue("Enter at least 6 Digit password");
            loggedMutableLive.setValue(false);
            return;
        } else if (!loggedIn.email.equals(email) || !loggedIn.password.equals(password)){
            EmailAddress.setValue("Masukkan alamat email Anda dengan benar");
            Password.setValue("Masukkan password Anda dengan benar");
            loggedMutableLive.setValue(false);
            return;
        }

    }
    public LiveData<LoggedIn> getLogin(){
        return this.loggedInMutableLiveData;
    }

    public LiveData<Boolean> loggedLiveData() {
        return this.loggedMutableLive;
    }

}
