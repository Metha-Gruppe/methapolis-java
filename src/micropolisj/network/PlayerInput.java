package micropolisj.network;

import java.io.Serializable;

import micropolisj.engine.ToolStroke;

/**
 * object encapsulating the actions of a player in order to send them to the server
 * @author nikolaibobenko
 *
 */
public class PlayerInput implements Serializable{
    
    //TODO allow inputs other than tools
    
    private ToolStroke toolStroke;

    public PlayerInput(ToolStroke tool) {
        toolStroke = tool;
    }
    
    public ToolStroke getToolStroke() {
        return toolStroke;
    }
}
