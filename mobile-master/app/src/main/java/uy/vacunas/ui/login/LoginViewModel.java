package uy.vacunas.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

//import uy.vacunas.data.LoginRepository;
//import uy.vacunas.data.Result;
//import uy.vacunas.data.model.LoggedInUser;
import uy.vacunas.R;

//public class LoginViewModel extends ViewModel {

   // private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
   // private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
   // private LoginRepository loginRepository;

   // LoginViewModel(LoginRepository loginRepository) {
     //   this.loginRepository = loginRepository;
    //}

    /*iveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);


    }

    public void loginDataChanged(String username, String password) { }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }*/
//}