package micropolisj.network;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.Map.Entry;

import micropolisj.engine.Micropolis;
import micropolisj.engine.ToolStroke;
import micropolisj.network.PlayerInput.BudgetInput;

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
            if(stroke != null) {
                stroke.setCity(this);
                stroke.apply();
            }
            BudgetInput budgetNum = entry.getKey().getBudgetNumbers();
            //TODO: Apply budgetNumbers to the right playerInfo
        }
    }
    
    //custom: 
    private MapInfo generateMapInfo() {
        MapInfo mapInfo = new MapInfo(map, sprites);
        mapInfo.cityBudget = playerInfo.budget;
        return mapInfo;
    }
}
