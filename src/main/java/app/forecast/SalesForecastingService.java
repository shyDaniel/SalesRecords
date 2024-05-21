package app.forecast;

import app.entity.Sale;
import app.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesForecastingService {

    @Autowired
    private SalesRepository salesRepository;

    public double forecastNextDaySales() throws InterruptedException {
        List<Sale> last7DaysSales = salesRepository.findTop7ByOrderByDateDesc();

        if (last7DaysSales.size() < 7) {
            throw new IllegalArgumentException("Not enough data to forecast.");
        }

        // Implement your forecasting logic here
        // For simplicity, we'll assume a simple moving average
        Thread.sleep(3000);
        double total = last7DaysSales.stream().mapToDouble(Sale::getAmount).sum();
        return total / 7;
    }
}
