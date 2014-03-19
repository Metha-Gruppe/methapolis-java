package micropolisj.network;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.Map.Entry;

import micropolisj.engine.CityBudget;
import micropolisj.engine.Micropolis;
import micropolisj.engine.ToolStroke;

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
        server.setMapInfo(generateMapInfo());
        Map<PlayerInput, Integer> inputs = server.getInput();
        for(Entry<PlayerInput, Integer> entry : inputs.entrySet()) {
            //TODO: react to playerID
            ToolStroke stroke = entry.getKey().getToolStroke();
            stroke.setCity(this);
            stroke.apply();
            
        }
    }
    
    //custom: 
    private MapInfo generateMapInfo() {
        MapInfo mapInfo = new MapInfo(map, sprites);
        mapInfo.cityBudget = this.budget;
        return mapInfo;
    }
}
