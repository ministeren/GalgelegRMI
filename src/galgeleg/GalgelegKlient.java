package galgeleg;

import java.net.URL;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class GalgelegKlient {
    
    public static void main(String[] args) throws Exception {
        
//        Galgeleg galleg = (Galgeleg) Naming.lookup("rmi://localhost/galge");   
        
        
        
        URL urlGalge = new URL("http://localhost:9933/kontotjeneste?wsdl");
        QName qnameGalge = new QName("http://galgeleg/", "GalgelegImplService");
        Service serviceGalge = Service.create(urlGalge, qnameGalge);
        Galgeleg galleg = serviceGalge.getPort(Galgeleg.class);        
        GalgelegLogik gl = galleg.hentLogik();
        
        brugerautorisation.transport.soap.Brugeradminklient bak = new brugerautorisation.transport.soap.Brugeradminklient();
        brugerautorisation.transport.soap.Brugeradmin ba = bak.getBa();
        
        System.out.println(ba.hentBruger("s144843", "sjakalen").fornavn);
        
        gl.nulstil();        
//        gl.logStatus();
        
        Scanner scanner = new Scanner(System.in); // opret scanner-objekt
        
        while (true) try {
            
            System.out.println(gl.getSynligtOrd());
            System.out.println("Antal forkerte: "+gl.getAntalForkerteBogstaver());
            System.out.println("Gæt på et bogstav: ");
            
            String valg = scanner.next();            
            scanner.nextLine();
            
            gl.gætBogstav(valg);            
            System.out.println();
            
            if (gl.erSpilletSlut()) {
                if(gl.erSpilletVundet()){
                    System.out.println("Tillykke du har vundet!");
                } else {
                    System.out.println("Du har desværre tabt");
                }
                System.out.println("");
                break;
            }
            
        } catch (Throwable t) { t.printStackTrace(); scanner.nextLine(); }
        
        System.out.println("Afslutter programmet... ");
        System.exit(0);

    }
}
