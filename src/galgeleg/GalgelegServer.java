/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package galgeleg;

import java.io.IOException;
import java.rmi.Naming;

/**
 *
 * @author Christian
 */
public class GalgelegServer {
    
    public static void main(String[] arg) throws IOException{
        
        GalgelegLogik gl = new GalgelegLogik();        
        GalgelegImpl impl = new GalgelegImpl(gl);
        
        java.rmi.registry.LocateRegistry.createRegistry(1099); // start rmiregistry i server-JVM        
        Naming.rebind("rmi://localhost/galge", impl);        
    }
    
}
