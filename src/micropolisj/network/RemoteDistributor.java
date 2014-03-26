package micropolisj.network;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import micropolisj.engine.Micropolis;
import micropolisj.engine.PlayerInfo;

/**
 * zentrale zugriffsklasse auf serverseite. bekommt von aussen mapobjekt reingereicht.
 * @author nikolaibobenko
 *
 */
public class RemoteDistributor extends UnicastRemoteObject implements IMicropolisServer{
    
    
    private transient ServerMicropolis micropolis;
    private MapInfo mapInfo;
    private Map<PlayerInput, Integer> events;
    
    private String levelName;
    
    private static int playerCount = 1;
    
    
    protected RemoteDistributor(ServerMicropolis micropolis) throws RemoteException {
        super();
        this.micropolis = micropolis;
        events = Collections.synchronizedMap(new HashMap<PlayerInput, Integer>());
    }
    
    

    @Override
    public synchronized MapInfo getLatestMap() throws RemoteException {
        // TODO --- Threadsafety
        return mapInfo;
    }
    
    public synchronized void setMapInfo(MapInfo mI) {
        mapInfo = mI;
    }

    @Override
    public synchronized void storeInput(int playerID, PlayerInput input) throws RemoteException {
        events.put(input, playerID);
    }
    
    public synchronized Map<PlayerInput, Integer> getInput() {
        return events;
    }

    @Override
    public void setRemoteClient(RemoteClient client) throws RemoteException {
        
    }
    
    public void clearInput() {
        events.clear();
    }
    
    @Override
    public byte[] getLevel() throws RemoteException {
        return null;
        //send .cty-file 
    }



    @Override
    public int getNewID() throws RemoteException {
        micropolis.addNewPlayer(playerCount);
        return playerCount++;
    }



    @Override
    public synchronized PlayerInfo getPlayerInfo(int ID) throws RemoteException{
        return micropolis.getPlayerInfo(ID);
    }

}
