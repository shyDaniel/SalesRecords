package app.forecast;

public class ForecastResult {
    private double predictedValue;
    private double upperBound;
    private double lowerBound;
    private double confidence;

    public ForecastResult(double predictedValue, double upperBound, double lowerBound, double confidence) {
        this.predictedValue = predictedValue;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.confidence = confidence;
    }

    public double getPredictedValue() {
        return predictedValue;
    }

    public void setPredictedValue(double predictedValue) {
        this.predictedValue = predictedValue;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(double upperBound) {
        this.upperBound = upperBound;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
