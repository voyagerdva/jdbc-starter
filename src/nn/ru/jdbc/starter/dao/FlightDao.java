package nn.ru.jdbc.starter.dao;

public class FlightDao {
    private static final FlightDao INSTANCE = new FlightDao();

    private FlightDao() {
    }

    public static FlightDao getINSTANCE() {
        return INSTANCE;
    }

}
