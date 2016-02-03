/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galgeleg;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Christian
 */
public class GalgelegImpl extends UnicastRemoteObject implements Galgeleg{
    
    GalgelegLogik gl;
    
    public GalgelegImpl(GalgelegLogik gal) throws java.rmi.RemoteException{
        gl = gal;
    }

    @Override
    public GalgelegLogik hentLogik() throws RemoteException {
        return gl;
    }    
    
}