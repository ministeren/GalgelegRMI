package brugerautorisation.transport.soap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author j
 */
public class Brugeradminklient {
    
    private Brugeradmin ba;
    
    public Brugeradminklient() throws MalformedURLException {
        
        URL urlBruger = new URL("http://javabog.dk:9901/brugeradmin?wsdl");
        QName qnameBruger = new QName("http://soap.transport.brugerautorisation/", "BrugeradminImplService");
        Service serviceBruger = Service.create(urlBruger, qnameBruger);
        ba = serviceBruger.getPort(Brugeradmin.class);
        
        
    }
    
    public Brugeradmin getBa(){
        return ba;
    }
}
