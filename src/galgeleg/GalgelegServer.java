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
        
        System.out.println("Publicerer tjenester");
        GalgelegLogik gl = new GalgelegLogik();
        GalgelegImpl gimpl = new GalgelegImpl(gl);
        BrugercheckImpl bimpl = new BrugercheckImpl();
        
// Ipv6-addressen [::] svarer til Ipv4-adressen 0.0.0.0, der matcher alle maskinens netkort og
        Endpoint.publish("http://[::]:9933/brugertjeneste", bimpl);
        Endpoint.publish("http://[::]:9934/galgetjeneste", gimpl);
        System.out.println("Tjenester publiceret");
    }
}
