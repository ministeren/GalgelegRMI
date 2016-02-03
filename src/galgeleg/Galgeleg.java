/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galgeleg;

/**
 *
 * @author Christian
 */
public interface Galgeleg extends java.rmi.Remote{
    
    GalgelegLogik hentLogik() throws java.rmi.RemoteException;
   
    
}
