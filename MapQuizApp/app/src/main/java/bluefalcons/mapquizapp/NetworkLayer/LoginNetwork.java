package bluefalcons.mapquizapp.NetworkLayer;

import com.github.nkzawa.socketio.client.Socket;

import bluefalcons.mapquizapp.LoginActivity;

/**
 * Created by Don Kerrigan on 11/21/2017.
 */

public class LoginNetwork {
    private static LoginNetwork mLoginNetInstance;
    private Socket mSocket;
    private static LoginActivity mLoginActivity;

    private LoginNetwork(){

    }

    public static LoginNetwork getInstance(LoginActivity loginActivity){
        mLoginActivity = loginActivity;
        if(mLoginNetInstance==null) {
            mLoginNetInstance = new LoginNetwork();
        }
        return mLoginNetInstance;
    }

    public void SetSocket(Socket socket){
        mSocket = socket;
    }

    public void Login(String user){
        mSocket.emit("login", user);
    }

    public void SetupSocketListeners() {
        //Listener for login response from server following a request
        mSocket.on("login", (data) -> {
            try {
                if (data[0] != null)
                    mLoginActivity.LoginCallback(true);
                else
                    mLoginActivity.LoginCallback(false);
            } catch (Exception e) {
            }
        });
    }
}
