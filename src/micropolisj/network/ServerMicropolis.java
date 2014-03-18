package micropolisj.network;

import java.rmi.RemoteException;

import micropolisj.engine.Micropolis;

public class ServerMicropolis extends Micropolis{
    
    private RemoteDistributor server;
    private NetworkServer socketBuilder;
    
    public ServerMicropolis() {
        super();
        try {
            server = new RemoteDistributor(this);
            socketBuilder = new NetworkServer(server);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Override
    public void animate() {
        super.animate();
        server.setMapInfo(new MapInfo(map, sprites));
    }
}
