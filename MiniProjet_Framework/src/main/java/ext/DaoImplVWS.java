package ext;

import dao.IDao;
import org.springframework.stereotype.Component;

@Component("dao3")
public class DaoImplVWS implements IDao {
    @Override
    public double getData() {
        System.out.println("Version web service");
        return 90;
    }
}
