package ext;

import dao.IDao;
import org.springframework.stereotype.Component;

@Component("dao3")
public class DaoImplVWS implements IDao {
    @Override
    public double getData() {
        return 0;
    }
}
