package bluefalcons.mapquizapp.NetworkLayer;

import com.github.nkzawa.socketio.client.Socket;

/**
 * Created by Don Kerrigan on 12/7/2017.
 */

public class UserNetwork {
    private static UserNetwork mUserNetInstance;
    private Socket mSocket;

    private UserNetwork(){

    }

    public static UserNetwork getInstance(){
        if(mUserNetInstance==null) {
            mUserNetInstance = new UserNetwork();
        }
        return mUserNetInstance;
    }

    public void SetSocket(Socket socket){
        mSocket = socket;
    }

    public void UpdateScore(String user){
        mSocket.emit("updateUser", user);
    }

    public void SetupSocketListeners() {
        //Listener for login response from server following a request
        /*mSocket.on("updateUser", (data) -> {
            try {
                if (data[0] != null)
                    mLoginActivity.LoginCallback(data[0].toString());
                else
                    mLoginActivity.LoginCallback(data[0].toString());
            } catch (Exception e) {
            }
        });*/
    }

}
