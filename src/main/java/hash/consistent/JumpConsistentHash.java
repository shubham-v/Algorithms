package hash.consistent;

/**
 * https://arxiv.org/pdf/1406.2294.pdf
 *
 * int32_t JumpConsistentHash(uint64_t key, int32_t num_buckets) {
 *         int64_t b = -1, j = 0;
 *         while (j < num_buckets) {
 *             b = j;
 *             key = key * 2862933555777941757ULL + 1;
 *             j = (b + 1) * (double(1LL << 31) / double((key >> 33) + 1));
 *         }
 *         return b;
 * }
 */
public class JumpConsistentHash {

    private static final long UNSIGNED_MASK = 0x7fffffffffffffffL;
    private static final long JUMP = 1L << 31;
    private static final long CONSTANT = Long.parseUnsignedLong("2862933555777941757");

    public static int hash(final Object o, final int buckets) {
        return hash(o.hashCode(), buckets);
    }

    public static int hash(final long key, final int buckets) {
        checkBuckets(buckets);
        long k = key;
        long b = -1;
        long j = 0;

        while (j < buckets) {
            b = j;
            k = k * CONSTANT + 1L;

            j = (long) ((b + 1L) * (JUMP / toDouble((k >>> 33) + 1L)));
        }
        return (int) b;
    }

    private static void checkBuckets(final int buckets) {
        if (buckets < 0) {
            throw new IllegalArgumentException("Buckets cannot be less than 0");
        }
    }

    private static double toDouble(final long n) {
        double d = n & UNSIGNED_MASK;
        if (n < 0) {
            d += 0x1.0p63;
        }
        return d;
    }

}
