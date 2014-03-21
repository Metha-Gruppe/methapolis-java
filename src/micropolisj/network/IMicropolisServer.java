package micropolisj.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMicropolisServer extends Remote{
    
    public MapInfo getLatestMap() throws RemoteException;
    
    public int getNewID() throws RemoteException;
    
    public void storeInput(int playerID, PlayerInput input) throws RemoteException;
    
    public void setRemoteClient(RemoteClient client) throws RemoteException;

    byte[] getLevel() throws RemoteException;
    
    
    
}
