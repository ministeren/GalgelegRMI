package galgeleg;

import java.rmi.Naming;

public class GalgelegKlient {

  public static void main(String[] args) throws Exception {
      
      Galgeleg galleg = (Galgeleg) Naming.lookup("rmi://localhost/galge");
      
      GalgelegLogik gl = galleg.hentLogik();
      
///*
//    try {
//      gl.hentOrdFraDr();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//*/


    gl.nulstil();
    gl.logStatus();

    gl.gætBogstav("e");
    gl.logStatus();

    gl.gætBogstav("a");
    gl.logStatus();
    System.out.println("" + gl.getAntalForkerteBogstaver());
    System.out.println("" + gl.getSynligtOrd());
    if (gl.erSpilletSlut()) return;

    gl.gætBogstav("i");
    gl.logStatus();
    if (gl.erSpilletSlut()) return;

    gl.gætBogstav("s");
    gl.logStatus();
    if (gl.erSpilletSlut()) return;

    gl.gætBogstav("r");
    gl.logStatus();
    if (gl.erSpilletSlut()) return;

    gl.gætBogstav("l");
    gl.logStatus();
    if (gl.erSpilletSlut()) return;

    gl.gætBogstav("b");
    gl.logStatus();
    if (gl.erSpilletSlut()) return;

    gl.gætBogstav("o");
    gl.logStatus();
    if (gl.erSpilletSlut()) return;

    gl.gætBogstav("t");
    gl.logStatus();
    if (gl.erSpilletSlut()) return;

    gl.gætBogstav("n");
    gl.logStatus();
    if (gl.erSpilletSlut()) return;

    gl.gætBogstav("m");
    gl.logStatus();
    if (gl.erSpilletSlut()) return;

    gl.gætBogstav("y");
    gl.logStatus();
    if (gl.erSpilletSlut()) return;

    gl.gætBogstav("p");
    gl.logStatus();
    if (gl.erSpilletSlut()) return;

    gl.gætBogstav("g");
    gl.logStatus();
    if (gl.erSpilletSlut()) return;
  }
}
