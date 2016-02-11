package galgeleg;

import java.net.URL;
import java.util.Scanner;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class GalgelegKlient {    
    public static void main(String[] args) throws Exception {

        boolean userChoice = false;
        String userWord = "";
        Scanner scanner = new Scanner(System.in); // opret scanner-objekt
        String user;
        String pass;
        
        URL urlBruger = new URL("http://localhost:9943/brugertjeneste?wsdl");
        QName qnameBruger = new QName("http://galgeleg/", "BrugercheckImplService");
        Service serviceBruger = Service.create(urlBruger, qnameBruger);
        BrugercheckInt brugerCheck = serviceBruger.getPort(BrugercheckInt.class);        

        URL urlGalge = new URL("http://localhost:9944/galgetjeneste?wsdl");
        QName qnameGalge = new QName("http://galgeleg/", "GalgelegImplService");
        Service serviceGalge = Service.create(urlGalge, qnameGalge);
        GalgelegInt galleg = serviceGalge.getPort(GalgelegInt.class);        
        GalgelegLogik gl = galleg.hentLogik();
        
        while(true){
            System.out.println("Indtast brugernavn:");
            user = scanner.next();            
            scanner.nextLine();
            
            System.out.println("");
            System.out.println("Indtast password:");
            pass = scanner.next();            
            scanner.nextLine();
            
            try{
                String navn = brugerCheck.checkBruger(user, pass);
                if(navn!=null){
                    System.out.println("");
                    System.out.println("Korrekt brugernavn og password");
                    System.out.println("Velkommen til Galgeleg "+navn);
                    System.out.println("");
                    break;
                }
            } catch (com.sun.xml.ws.fault.ServerSOAPFaultException e){
                System.out.println("");
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
                    gl.setOrdet(userWord);
                    userChoice = true;
                    break;
                case 2:
                    System.out.println("");
                    gl.setOrdetAuto();
                    userChoice = true;
                    break;
                default:
                    System.out.println("Du skal vælge 1 eller 2");
            }
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
                System.out.println("Ordet var: "+gl.getOrdet());
                System.out.println("");
                break;
            }
            
        } catch (Throwable t) { t.printStackTrace(); scanner.nextLine(); }
        
        System.out.println("Afslutter programmet... ");
        System.exit(0);
    }
}
