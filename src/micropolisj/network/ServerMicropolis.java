package micropolisj.network;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import micropolisj.engine.GameLevel;
import micropolisj.engine.Micropolis;
import micropolisj.engine.PlayerInfo;
import micropolisj.engine.ToolStroke;
import micropolisj.network.PlayerInput.BudgetInput;
import micropolisj.research.ResearchData;

public class ServerMicropolis extends Micropolis {

	private RemoteDistributor server;
	private NetworkServer socketBuilder;
	private Map<Integer, PlayerInfo> playerInfos;

	// constructor
	// setup new server
	public ServerMicropolis() {
		this(null, null);
	}

	// recreate server
	public ServerMicropolis(RemoteDistributor server, Map<Integer, PlayerInfo> playerInfos) {
		super();
		try {
			if(server == null) {
				this.server = new RemoteDistributor(this);
			}
			else {
				this.server = server;
			}
			socketBuilder = new NetworkServer(this.server);
			if(playerInfos == null) {
				this.playerInfos = new HashMap<Integer, PlayerInfo>();
				this.playerInfos.put(0, new PlayerInfo(this));
			}
			else {
				this.playerInfos = playerInfos;
			}
		}
		catch(RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void animate() {
		if(this.acycle % 2 == 0) {
			super.animate();
		}
		else {
			for(Entry<Integer, PlayerInfo> playerInf : playerInfos.entrySet()) {
				playerInfo = playerInf.getValue();
				super.animate();
				fcycle--;
				acycle--;
			}
			fcycle++;
		}

		// gives remoteDistributor MapInfo
		server.setMapInfo(generateMapInfo());
		Map<PlayerInput, Integer> inputs = server.getInput();
		// reacts to ClientInput
		for(Entry<PlayerInput, Integer> entry : inputs.entrySet()) {
			// TODO: react to playerID
			PlayerInput input = entry.getKey();
			PlayerInfo info = getPlayerInfo(entry.getValue());

			ToolStroke stroke = input.getToolStroke();
			if(stroke != null) {
				stroke.setCity(this);
				stroke.apply();
			}

			BudgetInput budgetNum = input.getBudgetNumbers();
			doBudgetInput(budgetNum, info);

			ResearchData researchData = input.getResearchData();
			doResearchInput(researchData, info);
		}
		server.clearInput();
	}

	private void doBudgetInput(BudgetInput bud, PlayerInfo pI) {
		if(bud != null) {
			pI.cityTax = bud.cityTax;
			pI.roadPercent = bud.roadPercent;
			pI.policePercent = bud.policePercent;
			pI.firePercent = bud.policePercent;
			pI.researchPercent = bud.researchPercent;
		}
	}

	private void doResearchInput(ResearchData data, PlayerInfo pI) {
		if(data != null) {
			pI.researchData.environmentResearch = data.environmentResearch;
			pI.researchData.fireResearch = data.fireResearch;
			pI.researchData.policeResearch = data.policeResearch;
			pI.researchData.rocketResearch = data.rocketResearch;
			pI.researchData.researchPoints = data.researchPoints;
		}
	}

	@Override
	public int getNumberOfPlayers() {
		return playerInfos.size();
	}

	@Override
	public PlayerInfo getPlayerInfo() {
		return getPlayerInfo(0);
	}

	@Override
	public PlayerInfo getPlayerInfo(int playerID) {
		return playerInfos.get(playerID);
	}

	private MapInfo generateMapInfo() {
		MapInfo mapInfo = new MapInfo(map, sprites, cityTime, gameWonID);
		// mapInfo.cityBudget = playerInfo.budget;
		return mapInfo;
	}

	public void addNewPlayer(int playerID) {
		PlayerInfo newPlayerInfo = new PlayerInfo(this);
		newPlayerInfo.budget.totalFunds = GameLevel.getStartingFunds(this.gameLevel);
		playerInfos.put(playerID, newPlayerInfo);
	}

	public RemoteDistributor getServer() {
		return server;
	}

	public void setServer(RemoteDistributor server) {
		this.server = server;
	}

	public NetworkServer getSocketBuilder() {
		return socketBuilder;
	}

	public void setSocketBuilder(NetworkServer socketBuilder) {
		this.socketBuilder = socketBuilder;
	}

	public Map<Integer, PlayerInfo> getPlayerInfos() {
		return playerInfos;
	}

	public void setPlayerInfos(Map<Integer, PlayerInfo> playerInfos) {
		this.playerInfos = playerInfos;
	}

}
