public class Pair<A extends Comparable<A>, B extends Comparable<B>>
        implements Comparable<Pair<A, B>> {
    private final A first;
    private final B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public boolean equals(Object otherItem) {
        if (otherItem instanceof Pair) {
            Pair otherPair = (Pair) otherItem;
            boolean firstItemEqual = false;
            boolean secondItemEqual = false;

            if (first == otherPair.first || (first != null && first.equals(otherPair.first))) {
                firstItemEqual = true;
            }

            if (second == otherPair.second || (second != null && second.equals(otherPair.second))) {
                secondItemEqual = true;
            }

            return firstItemEqual && secondItemEqual;
        }

        return false;
    }

    public int compareTo(Pair<A, B> otherPair) {
        int cmp = first.compareTo(otherPair.getFirst());

        if (cmp == 0) {
            cmp = second.compareTo(otherPair.getSecond());
        }

        return cmp;
    }

    public int hashCode() {
        int firstHash = first != null ? first.hashCode() : 0;
        int secondHash = second != null ? second.hashCode() : 0;

        return (firstHash + secondHash) * secondHash + firstHash;
    }

    public Pair<B, A> reverse() {
        return new Pair<B, A>(second, first);
    }
}
