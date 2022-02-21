package pres;

import dao.DaoImpl;
import ext.DaoImpl2;
import metier.MetierImpl;

public class Presenation {
    public static void main(String[] args) {
        /*
        * Injection des dépendances par instanciation statique =>new
        * */

        //DaoImpl dao=new DaoImpl();
        DaoImpl2 dao=new DaoImpl2();
        MetierImpl metier =new MetierImpl();
        metier.setDao(dao);
        System.out.println(metier.calcul());
    }
}
