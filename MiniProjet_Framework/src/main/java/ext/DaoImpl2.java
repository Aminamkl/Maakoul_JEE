package ext;

import dao.IDao;
import org.springframework.stereotype.Component;

@Component("dao2")
public class DaoImpl2 implements IDao {
    @Override
    public double getData() {
        System.out.println("Version capteur");
        double tmp =80;
        double res=tmp*10+Math.random()*40;
        return res;
    }
}
