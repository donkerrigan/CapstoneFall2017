package bluefalcons.mapquizapp.NetworkLayer;
import android.util.Log;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

/**
 * Created by Don Kerrigan on 10/31/2017.
 */

public class ServerConnection {
    private static ServerConnection serverInstance = null;

    /*
     * Change the below variable uri to your own server app domain.
     */
    private static String mServerURI = "http://capstone2017-bluefalcons.herokuapp.com";

    private ServerConnection(){
        try{
            mSocket.connect();
        }catch(Exception e){
            Log.d("SERVER ERROR", e.getMessage());
        }
    }

    public static ServerConnection getInstance(){
        if(serverInstance==null)
            serverInstance = new ServerConnection();

        return serverInstance;
    }

    private Socket mSocket;
    {
        try{
            mSocket = IO.socket(mServerURI);
        }catch(URISyntaxException e){
            Log.d("SERVER ERROR", e.getMessage());
        }
    }

    public Socket GetSocket(){
        return mSocket;
    }

    public void SendMessage(String _message){
        mSocket.emit("message", _message);

    }
}
