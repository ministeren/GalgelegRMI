/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galgeleg;

import java.net.MalformedURLException;
import java.rmi.server.UnicastRemoteObject;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author ministeren
 */
@WebService(endpointInterface = "galgeleg.BrugercheckInt")
public class BrugercheckImpl implements BrugercheckInt{
    
//    brugerautorisation.transport.soap.Brugeradminklient bak;
//    brugerautorisation.transport.soap.Brugeradmin ba;
//    
//    bak = new brugerautorisation.transport.soap.Brugeradminklient();
//    ba = bak.getBa();
    
    public String checkBruger(String user, String pass) throws MalformedURLException,com.sun.xml.ws.fault.ServerSOAPFaultException{
        return (new brugerautorisation.transport.soap.Brugeradminklient()).getBa().hentBruger(user, pass).fornavn;
    };
    
}
