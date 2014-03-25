package micropolisj.research;

import java.io.Serializable;

public class ResearchData implements Serializable{
    
    public int rocketResearch;
    public int policeResearch;
    public int fireResearch;
    public int environmentResearch;
    public int researchPoints;
    
    public boolean isRocketPossible() {
        return rocketResearch > 0;
    }
    
    public int getRocketRadius() {
        return rocketResearch * rocketResearch;
    }
}
