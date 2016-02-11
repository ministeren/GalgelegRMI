package galgeleg;

import com.cdyne.ws.DocumentSummary;
import com.cdyne.ws.Words;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class GalgelegLogik implements Serializable {
    
    private ArrayList<String> muligeOrd = new ArrayList<String>();
    private String ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private String synligtOrd;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;
    private boolean spilletErVundet;
    private boolean spilletErTabt;
    
    public GalgelegLogik() throws MalformedURLException {
        muligeOrd.add("bil");
        muligeOrd.add("computer");
        muligeOrd.add("programmering");
        muligeOrd.add("motorvej");
        muligeOrd.add("busrute");
        muligeOrd.add("gangsti");
        muligeOrd.add("skovsnegl");
        muligeOrd.add("solsort");
    }
    
    public ArrayList<String> getBrugteBogstaver() {
        return brugteBogstaver;
    }
    
    public String getSynligtOrd() {
        return synligtOrd;
    }
    
    public String getOrdet() {
        return ordet;
    }
    
    public void setOrdet(String in) {
        
        DocumentSummary ds = checkTextBodyV2(in);
        
        List<String> rigtigeOrd = null;
        
        for (Words w : ds.getMisspelledWord()) {
            rigtigeOrd = w.getSuggestions();
        }
        
        if(rigtigeOrd==null){
            System.out.println("Ordet er rigtigt");
            System.out.println("");
            ordet = in;
        } else if (rigtigeOrd.isEmpty()){
            System.out.println("Ordet er ukendt, der vælges et indbygget");
            System.out.println("");
            setOrdetAuto();
        } else {
            System.out.println("Ordet er forkert stavet, der vælges et tilfældigt rettet");
            System.out.println("");
            int choice = rigtigeOrd.size()/2;
            ordet = rigtigeOrd.get(choice);
        }
    }
    
    public void setOrdetAuto() {
        ordet = muligeOrd.get(new Random().nextInt(muligeOrd.size()));
    }
    
    public int getAntalForkerteBogstaver() {
        return antalForkerteBogstaver;
    }
    
    public boolean erSidsteBogstavKorrekt() {
        return sidsteBogstavVarKorrekt;
    }
    
    public boolean erSpilletVundet() {
        return spilletErVundet;
    }
    
    public boolean erSpilletTabt() {
        return spilletErTabt;
    }
    
    public boolean erSpilletSlut() {
        return spilletErTabt || spilletErVundet;
    }
    
    public void nulstil() {
        brugteBogstaver.clear();
        antalForkerteBogstaver = 0;
        spilletErVundet = false;
        spilletErTabt = false;
        opdaterSynligtOrd();
    }
    
    private void opdaterSynligtOrd() {
        synligtOrd = "";
        spilletErVundet = true;
        for (int n = 0; n < ordet.length(); n++) {
            String bogstav = ordet.substring(n, n + 1);
            if (brugteBogstaver.contains(bogstav)) {
                synligtOrd = synligtOrd + bogstav;
            } else {
                synligtOrd = synligtOrd + "*";
                spilletErVundet = false;
            }
        }
    }
    
    public void gætBogstav(String bogstav) {
        if (bogstav.length() != 1) return;
        System.out.println("Der gættes på bogstavet: " + bogstav);
        if (brugteBogstaver.contains(bogstav)) return;
        if (spilletErVundet || spilletErTabt) return;
        
        brugteBogstaver.add(bogstav);
        
        if (ordet.contains(bogstav)) {
            sidsteBogstavVarKorrekt = true;
            System.out.println("Bogstavet var korrekt: " + bogstav);
        } else {
            // Vi gættede på et bogstav der ikke var i ordet.
            sidsteBogstavVarKorrekt = false;
            System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
            antalForkerteBogstaver = antalForkerteBogstaver + 1;
            if (antalForkerteBogstaver > 6) {
                spilletErTabt = true;
            }
        }
        opdaterSynligtOrd();
    }
    
    public void logStatus() {
        System.out.println("---------- ");
        System.out.println("- ordet (skult) = " + ordet);
        System.out.println("- synligtOrd = " + synligtOrd);
        System.out.println("- forkerteBogstaver = " + antalForkerteBogstaver);
        System.out.println("- brugeBogstaver = " + brugteBogstaver);
        if (spilletErTabt) System.out.println("- SPILLET ER TABT");
        if (spilletErVundet) System.out.println("- SPILLET ER VUNDET");
        System.out.println("---------- ");
    }
    
    public static String hentUrl(String url) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje + "\n");
            linje = br.readLine();
        }
        return sb.toString();
    }
    
    public void hentOrdFraDr() throws Exception {
        String data = hentUrl("http://dr.dk");
        System.out.println("data = " + data);
        
        data = data.replaceAll("<.+?>", " ").toLowerCase().replaceAll("[^a-zæøå]", " ");
        System.out.println("data = " + data);
        muligeOrd.clear();
        muligeOrd.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));
        
        System.out.println("muligeOrd = " + muligeOrd);
        nulstil();
    }

    private static DocumentSummary checkTextBodyV2(java.lang.String bodyText) {
        com.cdyne.ws.Check service = new com.cdyne.ws.Check();
        com.cdyne.ws.CheckSoap port = service.getCheckSoap12();
        return port.checkTextBodyV2(bodyText);
    }
}
