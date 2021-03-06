package micropolisj.network;

import java.rmi.RemoteException;

import micropolisj.engine.MapState;
import micropolisj.engine.Micropolis;
import micropolisj.engine.PlayerInfo;
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
        PlayerInfo playerInfo = remote.getPlayerInfo();
        
        applyPlayerInfo(playerInfo);
        applyMapInfo(info);
        
//        super.animate();
        if(this.acycle % 2 == 0) {
			super.animate();
		}
        
        
        animateTiles();
        moveObjects();
//        TODO: take census every so many steps
//        takeCensus();
//        takeCensus2();
        fireEvents();
    }
    
    private void applyMapInfo(MapInfo info) {
        if(info != null)    {
            if(info.gameWonID != Integer.MAX_VALUE) {
                notifyEnd();
            }
//            map = info.map;
            for(int x = 0; x < info.map.length; x++) {
                for(int y = 0; y < info.map[0].length; y++) {
                    if(map[x][y] != info.map[x][y]) {
                        map[x][y] = info.map[x][y];
//                        System.out.println(x + ", " + y);
                        fireTileChanged(y, x);
                    }
                }
            }
            sprites = info.sprites;
            for(Sprite sprite : sprites) {
                sprite.setMicropolis(this);
            }
            this.cityTime = info.cityTime;
//            playerInfo.budget.setValues(info.cityBudget);
//            fireFundsChanged();
        }
    }
    
    private void fireEvents() {
        fireCensusChanged();
        fireEvaluationChanged();
        fireFundsChanged();
        fireDemandChanged();
//        fireMapOverlayDataChanged(MapState.ALL);
        
    }
    
    
    private void applyPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
        this.playerInfo.evaluation.setEngine(this);
    }
    
    public void toolUsed(ToolStroke tool) {
        PlayerInput input = new PlayerInput(tool);
        remote.sendInput(input);
    }
    
    @Override
    public void setBudgetNumbers(int newTaxRate, double roadPct, double newRoadPct, double newPolicePct, double newFirePct, double newResearchPct) {
        super.setBudgetNumbers(newTaxRate, roadPct, newRoadPct, newPolicePct, newFirePct, newResearchPct);
        PlayerInput budgetInput = new PlayerInput(null);
        budgetInput.setBudgetNumbers(newTaxRate, roadPct, newPolicePct, newFirePct, newResearchPct);
        remote.sendInput(budgetInput);
    }
    
    @Override
    public int getPlayerID() {
        return remote.getID();
    }
    @Override
    public void addResearchPoints(int playerID) {
		// used in collectTaxPartial()
		// Charger accumulates to Delay.
		// playerInfo.researchEffect needs a divison as it is 1000 base.
		// div 100 => you need at least 1 research station at 10% fund to get a
		// point
		if (researchDelayCharger >= researchDelay) {
			PlayerInfo info = getPlayerInfo(playerID);
			info.researchData.researchPoints += (info.researchEffect * this.getCityPopulation(playerID)) / (100 * 3000);
			
			PlayerInput input = new PlayerInput(null);
			input.setResearchData(info.researchData);
			remote.sendInput(input);
			
			info.researchData.print();

			if(info.researchState != null) {
				info.researchState.refreshPanel();				
			}
			researchDelayCharger = 0;
		} else {
			researchDelayCharger++;
		}
	}
    
    public String getPlayerIP() {
    	return remote.getIP();
    }
    
    public RemoteClient getRemote() {
    	return remote;
    }
    
    @Override
    public PlayerInfo getPlayerInfo(int playerID) {
        return playerInfo;
    }
}
