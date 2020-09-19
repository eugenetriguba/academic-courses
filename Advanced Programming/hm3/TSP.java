// Eugene Triguba <ytriguba17@ole.augie.edu>
// Advanced Programming: Homework 3
// TSP.java

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.List;

// Implement the traveling salesman problem. That is, write a program
// to identify a tour (a simple cycle) that has the salesman visit each
// city just once and finish up where he started, which is his home city.
// The order of visits should minimize the total distance traveled.
//
// Input: The home city followed by the other cites, one on each line.
// A blank line indicates no more cities. We assume the name of
// a city is a single uppercase letter. A sequence of integers
// one on each line, which gives the weight (distance) between
// a particular pair of cites, for all possible pair of cities.
//
// Output:
// 1. Prompt for the home city.
// 2. Prompt for other cities.
// 3. Each possible pair of cites
// 4. Each possible route staring from home and ending at home while
// visit every other city exactly once, along with the total weight
// of the route.
// 5. The minimum weight among all possible routes, and the route that
// achieves this minimum.
public class TSP {
    public static void main(String[] args) throws NoMoreInputException, EmptyLineException {
        Input in = new Input(System.in);

        Character homeCity = '\0';
        try {
            System.out.print("Home City: ");
            homeCity = in.readChar();
        } catch (EmptyLineException | NoMoreInputException e) {
            System.out.println("error: must enter a home city");
            System.exit(1);
        }

        System.out.println("Other cities, one on each line, newline to end: ");
        ArrayList<Character> otherCities = readOtherCities(in);
        if (otherCities.size() == 0) {
            System.out.println("error: must have at least one other city");
            System.exit(2);
        }

        ArrayList<Character> allCities = new ArrayList<>(otherCities);
        allCities.add(0, homeCity);

        System.out.println("Enter weight for each of the follow pairs: ");
        TreeSet<Pair<Character, Character>> pairs = findPairs(allCities);
        TreeMap<Pair<Character, Character>, Integer> weightedPairs = new TreeMap<>();
        for (Pair<Character, Character> p : pairs) {
            System.out.print(p + ": ");
            Integer weight = in.readInt();
            weightedPairs.put(p, weight);
            weightedPairs.put(p.reverse(), weight);
        }
        System.out.println();

        ArrayList<ArrayList<Character>> allRoutes = getAllRoutes(homeCity, otherCities);
        HashMap<ArrayList<Character>, Integer> weightedRoutes =
                getWeightedRoutes(allRoutes, weightedPairs);

        int minRouteWeight = Integer.MAX_VALUE;
        ArrayList<Character> minRoute = null;
        System.out.println("Weight for each of the following routes: ");
        Iterator<Entry<ArrayList<Character>, Integer>> iter = weightedRoutes.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<ArrayList<Character>, Integer> next = iter.next();
            if (next.getValue() < minRouteWeight) {
                minRouteWeight = next.getValue();
                minRoute = next.getKey();
            }

            System.out.println(next);
        }

        System.out.println();
        System.out.println(
                "Minimum weight " + minRouteWeight + " is achieved by the route " + minRoute);
    }

    private static ArrayList<Character> readOtherCities(Input in) {
        ArrayList<Character> otherCities = new ArrayList<Character>();

        while (in.hasNext()) {
            try {
                otherCities.add(in.readChar());
            } catch (EmptyLineException | NoMoreInputException e) {
                break;
            }
        }

        return otherCities;
    }

    private static <E extends Comparable<E>, T extends List<E>> TreeSet<Pair<E, E>> findPairs(
            T items) {
        int itemsLength = items.size();
        TreeSet<Pair<E, E>> pairs = new TreeSet<>();

        for (int i = 0; i < itemsLength; i++) {
            for (int j = i + 1; j < itemsLength; j++) {
                pairs.add(new Pair<E, E>(items.get(i), items.get(j)));
            }
        }

        return pairs;
    }

    private static <T> ArrayList<ArrayList<T>> getAllRoutes(T homeCity, ArrayList<T> cities) {
        ArrayList<ArrayList<T>> routes = Permutation.compute(cities);

        for (ArrayList<T> route : routes) {
            route.add(0, homeCity);
            route.add(homeCity);
        }

        return routes;
    }

    private static <T extends Comparable<T>> HashMap<ArrayList<T>, Integer> getWeightedRoutes(
            ArrayList<ArrayList<T>> allRoutes, TreeMap<Pair<T, T>, Integer> weightedPairs) {
        HashMap<ArrayList<T>, Integer> weightedRoutes = new HashMap<>();

        for (ArrayList<T> route : allRoutes) {
            int routeWeight = 0;

            // Subtract 1 because we'll be looking forward 1 index.
            int routeLength = route.size() - 1;
            for (int i = 0; i < routeLength; i++) {
                Pair<T, T> pair = new Pair<>(route.get(i), route.get(i + 1));
                routeWeight += weightedPairs.get(pair);
            }

            weightedRoutes.put(route, routeWeight);
        }

        return weightedRoutes;
    }
}
