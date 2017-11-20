package bluefalcons.mapquizapp.NetworkLayer;

import com.github.nkzawa.socketio.client.Socket;

import bluefalcons.mapquizapp.AppNavigation;

/**
 * Created by Don Kerrigan on 11/18/2017.
 */

public class AppNavigationNetwork {

    private static AppNavigationNetwork mAppNavInstance = null;
    private Socket mSocket;
    private static AppNavigation mAppNavigation;

    private AppNavigationNetwork(){

    }

    public static AppNavigationNetwork getInstance(AppNavigation appNavigation){
        mAppNavigation = appNavigation;
        if(mAppNavInstance==null)
            mAppNavInstance = new AppNavigationNetwork();
        return mAppNavInstance;
    }

    //Sets socket to handle all communication with server
    public void SetSocket(Socket socket){
        mSocket = socket;
    }

    //Starts listening for all events/responses from server needed for AppNavigation Activity
    public void SetupSocketListeners(){
        //Listener for login response from server following a request
        mSocket.on("login", (data) -> {
            try{
                if(data[0] != null)
                    mAppNavigation.LoginCallback(true);
                else
                    mAppNavigation.LoginCallback(false);
            }catch (Exception e){}
        });

        //Listener for a sign up response from the server following a request
        mSocket.on("signup", (data) -> {
            try{
                if(data[0] != null)
                    mAppNavigation.SignUpCallback(true);
                else
                    mAppNavigation.SignUpCallback(false);
            }catch (Exception e){}
        });

    }
}
