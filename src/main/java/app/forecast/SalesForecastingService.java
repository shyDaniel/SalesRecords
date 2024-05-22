package app.forecast;

import app.entity.Sale;
import app.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesForecastingService {

    @Autowired
    private SalesRepository salesRepository;

    public ForecastResult forecastNextDaySales(double confidence) {
        List<Sale> last7DaysSales = salesRepository.findTop7ByOrderByDateDesc();

        if (last7DaysSales.size() < 7) {
            throw new IllegalArgumentException("Not enough data to forecast.");
        }

        List<Double> salesAmounts = last7DaysSales.stream()
                .map(Sale::getAmount)
                .collect(Collectors.toList());

        double[] salesData = salesAmounts.stream().mapToDouble(Double::doubleValue).toArray();

        HoltWinters holtWinters = new HoltWinters(salesData, 0.5, 0.3, 0.2, 7);
        ForecastResult forecastResult = holtWinters.forecast(confidence); // 95% confidence interval

        return forecastResult;
    }
}
