package bluefalcons.mapquizapp.NetworkLayer;

import com.github.nkzawa.socketio.client.Socket;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import bluefalcons.mapquizapp.AppNavigation;
import bluefalcons.mapquizapp.JavaJsonConverter;

/**
 * Created by Don Kerrigan on 11/18/2017.
 */

public class AppNavigationNetwork {

    private static AppNavigationNetwork mAppNavInstance = null;
    private Socket mSocket;
    Gson gson;
    private static AppNavigation mAppNavigation;

    private AppNavigationNetwork(){
        gson = new Gson();
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
        mSocket.on("pingQuizzes", (data) -> {
            try{
                if(data[0] != null){//ADD CODE FOR A NETWORK CALLBACK
                    mAppNavigation.PingResponse(data.toString());
                }
                else{//ADD CODE FOR UNEXPECTED RESULT

                }
            }catch (Exception e){}
        });

        //Listener for a sign up response from the server following a request
        mSocket.on("signup", (data) -> {
            try{
                if(data[0] != null){//ADD CODE FOR A NETWORK CALLBACK
                    //
                }
                else{//ADD CODE FOR UNEXPECTED RESULT

                }
            }catch (Exception e){}
        });

    }

    public void PingQuizzes(double latitude, double longitude){
        LatLng latLng = new LatLng(latitude, longitude);
        this.mSocket.emit("pingQuizzes", gson.toJson(latLng));
    }
}
