package metier;

import annotations.Autowired;
import annotations.Component;
import dao.IDao;

@Component
public class MetierImpl implements IMetier{
    @Autowired
    private IDao dao=null;
    @Override
    public double calcul() {
        double tmp=dao.getData();
        double res=tmp*540/Math.cos(tmp*Math.PI);
        return res;
    }

    /**
     * Injecter dans la variable dao un objet d'une classe qui implémente l'interface IDao
     */
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
