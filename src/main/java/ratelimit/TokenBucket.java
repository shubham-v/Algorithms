package ratelimit;

public class TokenBucket {

    private final long maxBucketSize;
    private final double refillRate;

    private double currentBucketSize;
    private long lastRefillTimestamp;

    public TokenBucket(final long maxBucketSize, final double refillRate) {
        this.maxBucketSize = maxBucketSize;
        this.refillRate = refillRate;
        this.currentBucketSize = maxBucketSize;
        this.lastRefillTimestamp = System.nanoTime();
    }

    public synchronized boolean allowRequest(int tokens) {
        this.refill();

        if (this.currentBucketSize > tokens) {
            this.currentBucketSize -= tokens;
            return true;
        }

        return false;
    }

    private void refill() {
        long now = System.nanoTime();
        double tokensToAdd = (now - this.lastRefillTimestamp) * (this.refillRate / 1e9);
        this.currentBucketSize = Math.min(this.currentBucketSize + tokensToAdd, this.maxBucketSize);
        this.lastRefillTimestamp = now;
    }

//    public static void main(String[] args) {
//        new TokenBucket(100, 100/900). // 100 requests allowed in 15 mins
//                allowRequest(10);
//    }

}
