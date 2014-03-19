package micropolisj.network;

import java.rmi.RemoteException;

import micropolisj.engine.Micropolis;
import micropolisj.engine.Sprite;

public class ClientMicropolis extends Micropolis{
    
    private RemoteClient remote;
    
    public ClientMicropolis(String IP) throws RemoteException {
        super();
        remote = new RemoteClient(IP);
    }
    
    @Override
    public void animate() {
    	System.out.println(">>> animating in clientMicropolis");
        MapInfo info = remote.getMap();
        if(info != null)	{
            map = info.map;
            sprites = info.sprites;
            for(Sprite sprite : sprites) {
                sprite.setMicropolis(this);
            }
        }
        animateTiles();
        moveObjects();
    }
}
