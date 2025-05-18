package com.example.mapsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapsapp.SupabaseApplication
import com.example.mapsapp.utils.AuthState
import com.example.mapsapp.utils.SharedPreferencesHelper
import kotlinx.coroutines.launch

class AuthViewModel(private val sharedPreferences: SharedPreferencesHelper, private val logOut: Boolean = false) : ViewModel() {

    //Auth
    private val authManager = SupabaseApplication.supabase
    private val _email = MutableLiveData<String>()
    val email = _email
    private val _password = MutableLiveData<String>()
    val password = _password
    private val _authState = MutableLiveData<AuthState>()
    val authState = _authState
    private val _showError = MutableLiveData<Boolean>(false)
    val showError = _showError
    private val _user = MutableLiveData<String?>()
    val user = _user


    //comprovar si hi ha dades de usuari al sharedPreferences
    private fun checkExistingSession() {
        viewModelScope.launch {
            val accessToken = sharedPreferences.getAccessToken()
            val refreshToken = sharedPreferences.getRefreshToken()
            when {
                !accessToken.isNullOrEmpty() -> refreshToken()
                !refreshToken.isNullOrEmpty() -> refreshToken()
                else -> _authState.value = AuthState.Unauthenticated
            }
        }
    }

    //Editar email i password
    fun editEmail(value: String) {
        _email.value = value
    }

    fun editPassword(value: String) {
        _password.value = value
    }

    fun errorMessageShowed(){
        _showError.value = false
    }

    init {
        if (logOut) logout()
        else{
            checkExistingSession()
        }
    }


    //Registre d'usuari
    fun signUp() {
        viewModelScope.launch {
            _authState.value = authManager.signUpWithEmail(_email.value!!, _password.value!!)
            if (_authState.value is AuthState.Error) {
                _showError.value = true
            } else {
                val session = authManager.retrieveCurrentSession()
                sharedPreferences.saveAuthData(
                    session!!.accessToken,
                    session.refreshToken
                )
            }
        }
    }

    //Login d'usuari
    fun signIn() {
        viewModelScope.launch {
            _authState.value = authManager.signInWithEmail(_email.value!!, _password.value!!)
            if (_authState.value is AuthState.Error) {
                _showError.value = true
            } else {
                val session = authManager.retrieveCurrentSession()
                sharedPreferences.saveAuthData(
                    session!!.accessToken,
                    session.refreshToken
                )
            }
        }
    }

    //Mantenir login
    private fun refreshToken() {
        viewModelScope.launch {
            try {
                authManager.refreshSession()
                _authState.value = AuthState.Authenticated
            } catch (e: Exception) {
                sharedPreferences.clear()
                _authState.value = AuthState.Unauthenticated
            }
        }
    }

    //Logout
    fun logout() {
        viewModelScope.launch {
            sharedPreferences.clear()
            _authState.value = AuthState.Unauthenticated
        }
    }
}