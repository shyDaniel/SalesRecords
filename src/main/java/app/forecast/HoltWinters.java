package app.forecast;

public class HoltWinters {

    private final double[] data;
    private final double alpha;
    private final double beta;
    private final double gamma;
    private final int seasonLength;

    public HoltWinters(double[] data, double alpha, double beta, double gamma, int seasonLength) {
        this.data = data;
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
        this.seasonLength = seasonLength;
    }

    public ForecastResult forecast(double confidenceInterval) {
        int dataLength = data.length;

        double[] level = new double[dataLength];
        double[] trend = new double[dataLength];
        double[] season = new double[seasonLength];
        double[] seasonExtended = new double[dataLength];
        double[] forecast = new double[dataLength + 1];

        // Initialization
        level[0] = data[0];
        trend[0] = data[1] - data[0];

        for (int i = 0; i < seasonLength; i++) {
            season[i] = data[i] / level[0];
        }

        // Extend seasonality array
        for (int i = 0; i < dataLength; i++) {
            seasonExtended[i] = season[i % seasonLength];
        }

        // Holt-Winters equations
        for (int t = 1; t < dataLength; t++) {
            level[t] = alpha * (data[t] / seasonExtended[t]) + (1 - alpha) * (level[t - 1] + trend[t - 1]);
            trend[t] = beta * (level[t] - level[t - 1]) + (1 - beta) * trend[t - 1];
            seasonExtended[t] = gamma * (data[t] / level[t]) + (1 - gamma) * seasonExtended[t];
            forecast[t] = (level[t - 1] + trend[t - 1]) * seasonExtended[t];
        }

        // Forecast the next point
        forecast[dataLength] = (level[dataLength - 1] + trend[dataLength - 1]) * seasonExtended[dataLength % seasonLength];

        // Calculate upper and lower bounds
        double deviation = calculateDeviation(data, forecast, dataLength);
        double upperBound = forecast[dataLength] + confidenceInterval * deviation;
        double lowerBound = forecast[dataLength] - confidenceInterval * deviation;

        return new ForecastResult(forecast[dataLength], upperBound, lowerBound, confidenceInterval);
    }

    private double calculateDeviation(double[] data, double[] forecast, int dataLength) {
        double sum = 0.0;
        for (int i = 0; i < dataLength; i++) {
            sum += Math.pow(data[i] - forecast[i], 2);
        }
        return Math.sqrt(sum / dataLength);
    }
}
