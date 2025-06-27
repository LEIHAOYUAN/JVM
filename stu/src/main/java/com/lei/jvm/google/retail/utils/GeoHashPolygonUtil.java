//package com.lei.jvm.google.retail.utils;
//import com.github.davidmoten.geo.GeoHash;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class GeoHashPolygonUtil {
//
//    public static void main(String[] args) {
//        // Define polygon coordinates
//        List<double[]> polygonCoordinates = new ArrayList<>();
//        polygonCoordinates.add(new double[]{-73.99360656738281, 40.804595947265625});
//        polygonCoordinates.add(new double[]{-73.99223327636719, 40.79498291015625});
//        polygonCoordinates.add(new double[]{-73.99223327636719, 40.792236328125});
//        polygonCoordinates.add(new double[]{-74.00047302246094, 40.78125});
//        polygonCoordinates.add(new double[]{-74.00253295898438, 40.78056335449219});
//        polygonCoordinates.add(new double[]{-74.00321960449219, 40.77850341796875});
//        polygonCoordinates.add(new double[]{-73.99909973144531, 40.7977294921875});
//        polygonCoordinates.add(new double[]{-73.99703979492188, 40.79841613769531});
//        polygonCoordinates.add(new double[]{-73.99360656738281, 40.804595947265625});
//
//        // Generate geohashes inside the polygon
//        Set<String> geohashesInside = generateGeohashesFromPolygon(polygonCoordinates, 7);
//
//        // Output the geohashes
//        System.out.println("Geohashes inside the polygon: " + geohashesInside);
//        System.out.println("Number of geohashes: " + geohashesInside.size());
//    }
//
//    private static Set<String> generateGeohashesFromPolygon(List<double[]> polygonCoordinates, int precision) {
//        Set<String> geohashes = new HashSet<>();
//        // Get geohash for each point in the polygon
//        for (double[] coordinate : polygonCoordinates) {
//            double latitude = coordinate[1];
//            double longitude = coordinate[0];
//            String geohash = GeoHash.from(latitude, longitude, precision).toBase32();
//            geohashes.add(geohash);
//        }
//
//        return geohashes;
//    }
//}
