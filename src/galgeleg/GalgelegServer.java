/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package galgeleg;

import java.io.IOException;
import java.rmi.Naming;
import javax.xml.ws.Endpoint;

/**
 *
 * @author Christian
 */
public class GalgelegServer {
    
    public static void main(String[] arg) throws IOException{
        
        
        System.out.println("publicerer kontotjeneste");
        GalgelegLogik gl = new GalgelegLogik();
        GalgelegImpl impl = new GalgelegImpl(gl);
// Ipv6-addressen [::] svarer til Ipv4-adressen 0.0.0.0, der matcher alle maskinens netkort og
        Endpoint.publish("http://[::]:9933/kontotjeneste", impl);
        System.out.println("Kontotjeneste publiceret.");

//        GalgelegLogik gl = new GalgelegLogik();
//        GalgelegImpl impl = new GalgelegImpl(gl);
//
//        java.rmi.registry.LocateRegistry.createRegistry(1099); // start rmiregistry i server-JVM
//        Naming.rebind("rmi://localhost/galge", impl);
//        System.out.println("Server Running");

    }
    
}
