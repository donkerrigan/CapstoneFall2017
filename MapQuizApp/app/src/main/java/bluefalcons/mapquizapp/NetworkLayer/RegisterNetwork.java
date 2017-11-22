package bluefalcons.mapquizapp.NetworkLayer;

import com.github.nkzawa.socketio.client.Socket;

import bluefalcons.mapquizapp.RegisterActivity;

/**
 * Created by Don Kerrigan on 11/21/2017.
 */

public class RegisterNetwork {
    private static RegisterNetwork mRegisterNetInstance;
    private Socket mSocket;
    private static RegisterActivity mRegisterActivity;

    private RegisterNetwork(){

    }

    public static RegisterNetwork getInstance(RegisterActivity registerActivity){
        mRegisterActivity = registerActivity;
        if(mRegisterNetInstance==null) {
            mRegisterNetInstance = new RegisterNetwork();
        }
        return mRegisterNetInstance;
    }


    public void SetSocket(Socket socket){
        mSocket = socket;
    }


    public void SignUp(String user){mSocket.emit("signup", user);}

    public void SetupSocketListeners(){

        //Listener for a sign up response from the server following a request
        mSocket.on("signup", (data) -> {
            try{
                if(data[0] != null)
                    mRegisterActivity.SignUpCallback(true);
                else
                    mRegisterActivity.SignUpCallback(false);
            }catch (Exception e){}
        });
    }
}
