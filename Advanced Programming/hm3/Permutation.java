import java.util.ArrayList;
import java.util.Collections;

public class Permutation {

    public static <T> ArrayList<ArrayList<T>> compute(ArrayList<T> arr) {
        return permute(arr, new ArrayList<ArrayList<T>>(), 0);
    }

    private static <T> ArrayList<ArrayList<T>> permute(ArrayList<T> arr,
            ArrayList<ArrayList<T>> out, int index) {
        if (index == arr.size() - 1) {
            out.add(arr);
        } else {
            ArrayList<T> newArr = new ArrayList<T>(arr);
            permute(newArr, out, index + 1);

            for (int i = index + 1; i < arr.size(); i++) {
                newArr = new ArrayList<T>(arr);
                Collections.swap(newArr, index, i);
                permute(newArr, out, index + 1);
            }
        }

        return out;
    }
}
