package micropolisj.engine;

import micropolisj.research.ResearchState;

/**
 * holds all infos about one player on the map
 * @author nikolaibobenko
 *
 */
public class PlayerInfo {
    
    
    //TODO: decide which variables are playerrelated and which ones are maprelated!!!
    // playerrelated --> PlayerInfo
    // maprelated --> Micropolis
    
    public boolean [][]powermap;
    public CityBudget budget;
    
    public boolean autoBulldoze = true;
    public boolean autoBudget = false;
    
    // census numbers, reset in phase 0 of each cycle, summed during map scan
    public int poweredZoneCount;
    public int unpoweredZoneCount;
    public int roadTotal;
    public int railTotal;
    public int firePop;
    public int resZoneCount;
    public int comZoneCount;
    public int indZoneCount;
    public int resPop;
    public int comPop;
    public int indPop;
    public int hospitalCount;
    public int churchCount;
    public int policeCount;
    public int fireStationCount;
    public int stadiumCount;
    public int coalCount;
    public int nuclearCount;
    public int seaportCount;
    public int airportCount;
    
    // CUSTOM
    public int researchCount;

    public int totalPop;
    public int lastCityPop;

    // used in generateBudget()
    public int lastRoadTotal;
    public int lastRailTotal;
    public int lastTotalPop;
    public int lastFireStationCount;
    public int lastPoliceCount;
    public int lastResearchCount;

    public int trafficMaxLocationX;
    public int trafficMaxLocationY;
    public int pollutionMaxLocationX;
    public int pollutionMaxLocationY;
    public int crimeMaxLocationX;
    public int crimeMaxLocationY;
    public int centerMassX;
    public int centerMassY;
    
    public int needHospital; // -1 too many already, 0 just right, 1 not enough
    public int needChurch; // -1 too many already, 0 just right, 1 not enough

    public int crimeAverage;
    public int pollutionAverage;
    public int landValueAverage;
    public int trafficAverage;

    public int resValve; // ranges between -2000 and 2000, updated by setValves
    public int comValve; // ranges between -1500 and 1500
    public int indValve; // ranges between -1500 and 1500

    public boolean resCap; // residents demand a stadium, caps resValve at 0
    public boolean comCap; // commerce demands airport, caps comValve at 0
    public boolean indCap; // industry demands sea port, caps indValve at 0
    public int crimeRamp;
    public int polluteRamp;

    public int comPopAverage;
    public int indPopAverage;
    public int resPopAverage;
    
    //
    // budget stuff
    //
    public int cityTax = 7;
    public double roadPercent = 1.0;
    public double policePercent = 1.0;
    public double firePercent = 1.0;
    public double researchPercent = 1.0;

    public int taxEffect = 7;
    public int roadEffect = 32;
    public int policeEffect = 1000;
    public int fireEffect = 1000;
    public int researchEffect = 1000;

    public int cashFlow; // net change in totalFunds in previous year

    public boolean newPower;
    
    public ResearchState researchState;
    public CityEval evaluation;
    
    
    public PlayerInfo(Micropolis city) {
        budget = new CityBudget(city);
    }
}