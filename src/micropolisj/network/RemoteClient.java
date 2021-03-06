package micropolisj.network;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import micropolisj.engine.PlayerInfo;

public class RemoteClient extends UnicastRemoteObject implements Serializable{
    
    private transient IMicropolisServer server;
    
    private String IP;
    
    private int playerID;
    
    public RemoteClient(String ip) throws RemoteException{
        try {
            // TODO: make constants for address
            if(ip.equalsIgnoreCase("local") || ip.equalsIgnoreCase("localhost"))	{
            	ip = "127.0.0.1";
            }
            IP = ip;
            server = (IMicropolisServer) Naming.lookup("rmi://" + IP + "/" + NetworkServer.NAMING_BIND);
//            server.setRemoteClient(this);
            System.out.println(">>> connected to server...");
            playerID = server.getNewID();
            // System.out.println(playerID);
        } catch (MalformedURLException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public PlayerInfo getPlayerInfo() {
        try {
            return server.getPlayerInfo(playerID);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public int getID() {
        return playerID;
    }
    
    public void sendInput(PlayerInput input){
        try {
            server.storeInput(playerID, input);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public MapInfo getMap() {
        try {
            return server.getLatestMap();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public String getIP() {
    	return IP;
    }
    
}
