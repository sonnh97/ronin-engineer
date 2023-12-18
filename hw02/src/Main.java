import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {


        Map<String, AirportModel> sourceData = Map.of(
                "HN", new AirportModel("HN", "Ha Noi"),
                "HCM", new AirportModel("HN", "Ho Chi Minh"),
                "DN", new AirportModel("HN", "Da Nang")
        );

        DataHandler<String, AirportModel> airportModelDataHandler = sourceData::get;

        Cache<String, AirportModel> airportCache = new Cache<>(10000);
        airportCache.get("HN", airportModelDataHandler);
    }
}