package micropolisj.network;

import java.io.Serializable;

import micropolisj.engine.ToolStroke;
import micropolisj.research.ResearchData;

/**
 * object encapsulating the actions of a player in order to send them to the server
 * @author nikolaibobenko
 *
 */
public class PlayerInput implements Serializable{
    
    //TODO allow inputs other than tools
    
    private ToolStroke toolStroke;
    private BudgetInput budgetNumbers;
    private ResearchData researchData;
    

    public PlayerInput(ToolStroke tool) {
        toolStroke = tool;
    }
    
    public ToolStroke getToolStroke() {
        return toolStroke;
    }
    
    public void setBudgetNumbers(int cityTax, double roadPercent, double policePct, double firePct, double researchPct) {
        budgetNumbers = new BudgetInput();
        budgetNumbers.cityTax = cityTax;
        budgetNumbers.roadPercent = roadPercent;
        budgetNumbers.policePercent = policePct;
        budgetNumbers.firePercent = firePct;
        budgetNumbers.researchPercent = researchPct;
    }
    
    public void setResearchData(ResearchData data) {
    	researchData = data;
    }
    
    public class BudgetInput implements Serializable{
        public int cityTax;
        public double roadPercent;
        public double policePercent;
        public double firePercent;
        public double researchPercent;
    }

    public BudgetInput getBudgetNumbers() {
        return budgetNumbers;
    }
    
    public ResearchData getResearchData() {
    	return researchData;
    }
}
