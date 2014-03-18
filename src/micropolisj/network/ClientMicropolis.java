package micropolisj.network;

import java.rmi.RemoteException;

import micropolisj.engine.Micropolis;

public class ClientMicropolis extends Micropolis{
    
    private RemoteClient remote;
    
    public ClientMicropolis(String IP) throws RemoteException {
        super();
        remote = new RemoteClient(IP);
    }
    
    @Override
    public void animate() {
        MapInfo info = remote.getMap();
        map = info.map;
        sprites = info.sprites;
        animateTiles();
    }
}
