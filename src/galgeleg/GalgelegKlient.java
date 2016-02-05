package galgeleg;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

public class GalgelegKlient {
    
    public static void main(String[] args) throws Exception {
        
        Galgeleg galleg = (Galgeleg) Naming.lookup("rmi://localhost/galge");        
        GalgelegLogik gl = galleg.hentLogik();
        
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
