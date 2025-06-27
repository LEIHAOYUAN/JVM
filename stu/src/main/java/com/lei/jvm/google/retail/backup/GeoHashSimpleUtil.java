package com.lei.jvm.google.retail.backup;

/**
 *
 */
public class GeoHashSimpleUtil {

    private static final String BASE32 = "0123456789bcdefghjkmnpqrstuvwxyz";

    public static void main(String[] args) {
        double latitude = -73.99360656738281;
        double longitude = 40.804595947265625;
        int precision = 7;
        String geoHash = encode(latitude, longitude, precision);
        System.out.println("Geohash: " + geoHash);
    }

    public static String encode(double latitude, double longitude, int precision) {
        boolean isEven = true;
        int bit = 0, ch = 0;
        double[] lat = {-90.0, 90.0};
        double[] lon = {-180.0, 180.0};
        StringBuilder geohash = new StringBuilder();

        while (geohash.length() < precision) {
            double mid;
            if (isEven) {
                mid = (lon[0] + lon[1]) / 2D;
                if (longitude > mid) {
                    ch |= 1 << (4 - bit);
                    lon[0] = mid;
                } else {
                    lon[1] = mid;
                }
            } else {
                mid = (lat[0] + lat[1]) / 2D;
                if (latitude > mid) {
                    ch |= 1 << (4 - bit);
                    lat[0] = mid;
                } else {
                    lat[1] = mid;
                }
            }
            isEven = !isEven;
            if (bit < 4) {
                bit++;
            } else {
                geohash.append(BASE32.charAt(ch));
                bit = 0;
                ch = 0;
            }
        }
        return geohash.toString();
    }
}
