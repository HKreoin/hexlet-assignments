package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int count) {
        List<String> sorted = new ArrayList<String>();
        if (apartments.isEmpty()) {
            return sorted;
        }
        
        sorted = apartments
                .stream()
                .sorted(new HomeComparator())
                .map(Home::toString)
                .collect(Collectors.toList())
                .subList(0, count);
        return  sorted;
    }
}

class HomeComparator implements Comparator<Home>{

    public int compare(Home a, Home b){

        return a.compareTo(b);
    }
}
// END
