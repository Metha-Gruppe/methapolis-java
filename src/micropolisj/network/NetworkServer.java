package micropolisj.network;

import java.rmi.Naming;

public class NetworkServer {
    
    private IMicropolisServer server;
    
    
    public NetworkServer(IMicropolisServer server) {
        this.server = server;
        try {
             java.rmi.registry.LocateRegistry.createRegistry(1099);
             System.out.println("RMI registry ready.");
             Naming.bind("remoteLogic", this.server);

          } catch (Exception e) {
             e.printStackTrace();
          }
        }
      }
