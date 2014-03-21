package micropolisj.network;

import java.rmi.RemoteException;

import micropolisj.engine.Micropolis;
import micropolisj.engine.Sprite;
import micropolisj.engine.ToolStroke;

public class ClientMicropolis extends Micropolis{
    
    private RemoteClient remote;
    
    public ClientMicropolis(String IP) throws RemoteException {
        super();
        remote = new RemoteClient(IP);
    }
    
    @Override
    public void animate() {
        MapInfo info = remote.getMap();
        applyMapInfo(info);
        animateTiles();
        moveObjects();
    }
    
    private void applyMapInfo(MapInfo info) {
        if(info != null)    {
            map = info.map;
            sprites = info.sprites;
            for(Sprite sprite : sprites) {
                sprite.setMicropolis(this);
            }
            budget.setValues(info.cityBudget);
            fireFundsChanged();
        }
    }
    
    public void toolUsed(ToolStroke tool) {
        PlayerInput input = new PlayerInput(tool);
        remote.sendInput(input);
    }
    
    @Override
    public int getPlayerID() {
        return remote.getID();
    }
}
