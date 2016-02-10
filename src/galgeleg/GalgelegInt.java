/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galgeleg;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Christian
 */
@WebService
public interface GalgelegInt extends java.rmi.Remote{
    
    @WebMethod GalgelegLogik hentLogik() throws java.rmi.RemoteException;
   
    
}
