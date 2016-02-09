package galgeleg;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class GalgelegKlient {    
    public static void main(String[] args) throws Exception {

        boolean userChoice = false;
        boolean userOrd = false;
        String userWord = "";
        Scanner scanner = new Scanner(System.in); // opret scanner-objekt
        String user;
        String pass;
        brugerautorisation.transport.soap.Brugeradminklient bak;
        brugerautorisation.transport.soap.Brugeradmin ba;
        brugerautorisation.transport.soap.Bruger br;
        
        bak = new brugerautorisation.transport.soap.Brugeradminklient();
        ba = bak.getBa();
        
        while(true){
            System.out.println("Indtast brugernavn:");
            user = scanner.next();            
            scanner.nextLine();
            
            System.out.println("");
            System.out.println("Indtast password:");
            pass = scanner.next();            
            scanner.nextLine();
            
            try{
                br = ba.hentBruger(user, pass); 
                if(br!=null){
                    System.out.println("");
                    System.out.println("Korrekt brugernavn og password");
                    System.out.println("Velkommen til Galgeleg "+br.fornavn);
                    System.out.println("");
                    break;
                }
            } catch (com.sun.xml.ws.fault.ServerSOAPFaultException e){
                System.out.println("Forkert brugernavn eller password");
                System.out.println("");
            }
        }
        
        while(!userChoice){
            System.out.println("1: Indtast eget ord");
            System.out.println("2: Benyt indbygget ord");
            System.out.println("");
            int choice = scanner.nextInt();            
            scanner.nextLine();
            
            switch(choice){
                case 1:
                    System.out.println("");
                    System.out.println("Indtast ord:");
                    userWord = scanner.next();
                    scanner.nextLine();
                    System.out.println("");
                    userOrd = true;
                    userChoice = true;
                    break;
                case 2:
                    System.out.println("");
                    userChoice = true;
                    break;
                default:
                    System.out.println("Du skal vælge 1 eller 2");
            }
        }        
        
        URL urlGalge = new URL("http://localhost:9933/kontotjeneste?wsdl");
        QName qnameGalge = new QName("http://galgeleg/", "GalgelegImplService");
        Service serviceGalge = Service.create(urlGalge, qnameGalge);
        Galgeleg galleg = serviceGalge.getPort(Galgeleg.class);        
        GalgelegLogik gl = galleg.hentLogik();        
        
        if(userOrd){
            gl.setOrdet(userWord);
        } else {
            gl.setOrdetAuto();
        }
        
        gl.nulstil();
        
        while (true) try {
            
            System.out.println(gl.getSynligtOrd());
            System.out.println("Antal forkerte: "+gl.getAntalForkerteBogstaver());
            System.out.println("Brugte bogstaver: "+gl.getBrugteBogstaver());
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
