package bikini.potato.stars;

public class MainStars {

    public static void main(String[] args) {
        System.out.println(getStars(0));
        System.out.println(getStars(1));
        System.out.println(getStars(2));
        System.out.println(getStars(3));
        System.out.println(getStars(4));
        System.out.println(getStars(5));
    }

    protected static String getStars(int param) {

        long amount = calculateHowManyWeNeed(param);
        String result = "*";
        for(long i = 1; i < amount; i++) {
            result += "*";
        }
        return result;
    }

    protected static long calculateHowManyWeNeed(int param) {
    //since the specification doesn't mention anything about the range of possible values of the parameter this solution should be ok, for low numbers at least
        if(param == 0) {
            return 1;
        }

        long result = 1;
        for(int i = 1;i <= param; i++) {
            result *= 2;
        }
        return result;
    }
}
