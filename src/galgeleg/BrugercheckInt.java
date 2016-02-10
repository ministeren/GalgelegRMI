/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galgeleg;

import java.net.MalformedURLException;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author ministeren
 */
@WebService
public interface BrugercheckInt{
    
    @WebMethod String checkBruger(String user, String pass) throws MalformedURLException,com.sun.xml.ws.fault.ServerSOAPFaultException;
    
}
