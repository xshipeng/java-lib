package com.wavefront.ingester;

import com.wavefront.data.TooManyCentroidException;

public class IngesterContext {
  /**
   * Compress histogram limit when the count of centroid is larger then this ratio * accuracy.
   * Default to 2, meaning when the count of centroids is larger then twice of histogram
   * accuracy, perform the compression.
   */
  public static final double DEFAULT_HISTOGRAM_COMPRESS_LIMIT_RATIO = 2.0;

  /**
   * Default centroids count limit, default to 100, meaning histogram with more then 100
   * centroids will throw {@link TooManyCentroidException}.
   */
  public static final int DEFAULT_CENTROIDS_COUNT_LIMIT = 100;

  /**
   * Default histogram accuracy, used when constructing new T-Digest for compression, and also
   * controls the threshold for compression as well.
   */
  public static final int DEFAULT_HISTOGRAM_ACCURACY = 32;

  private int histogramCentroidsLimit;
  private int targetHistogramAccuracy;
  private boolean optimizeHistograms;

  public static class Builder {
    private int histogramCentroidsLimit = Integer.MAX_VALUE;
    private int targetHistogramAccuracy = DEFAULT_HISTOGRAM_ACCURACY;

    private boolean optimizeHistograms = false;

    public Builder withTargetHistogramAccuracy(int targetHistogramAccuracy) {
      this.targetHistogramAccuracy = targetHistogramAccuracy;
      return this;
    }

    public Builder throwIfTooManyHistogramCentroids(int histogramCentroidsLimit) {
      this.histogramCentroidsLimit = histogramCentroidsLimit;
      return this;
    }

    public Builder withOptimizeHistograms(boolean optimizeHistograms) {
      this.optimizeHistograms = optimizeHistograms;
      return this;
    }

    public IngesterContext build() {
      return new IngesterContext(this);
    }
  }

  private IngesterContext(Builder builder) {
    this.histogramCentroidsLimit = builder.histogramCentroidsLimit;
    this.targetHistogramAccuracy = builder.targetHistogramAccuracy;
    this.optimizeHistograms = builder.optimizeHistograms;
  }

  public int getHistogramCentroidsLimit() {
    return histogramCentroidsLimit;
  }

  public void setHistogramCentroidsLimit(int histogramCentroidsLimit) {
    this.histogramCentroidsLimit = histogramCentroidsLimit;
  }

  public int getTargetHistogramAccuracy() {
    return targetHistogramAccuracy;
  }

  public void setTargetHistogramAccuracy(int targetHistogramAccuracy) {
    this.targetHistogramAccuracy = targetHistogramAccuracy;
  }

  public boolean isOptimizeHistograms() {
    return optimizeHistograms;
  }

  public void setOptimizeHistograms(boolean optimizeHistograms) {
    this.optimizeHistograms = optimizeHistograms;
  }
}
