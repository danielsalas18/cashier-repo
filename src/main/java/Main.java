import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String ... args) {
        Map<Integer, Integer> registerStatus = new HashMap<Integer, Integer>();
        registerStatus.put(1, 30); // One Dolar, 30 bills;
        registerStatus.put(2, 3); // Two Dolar, 3 bills;
        registerStatus.put(5, 3); // Five Dolar, 3 bills;
        registerStatus.put(10, 2); // Ten Dolar, 2 bills;
        registerStatus.put(20, 2); // twenty Dolar, 2 bills;
        registerStatus.put(50, 2); // fifhty Dolar, 2 bills;
        registerStatus.put(100, 2); // One hundred Dolar, 2 bills;

        boolean result = canIGiveChange(registerStatus, 100, 9);
        System.out.printf("Result " + result);

    }

    public static boolean canIGiveChange(Map<Integer,Integer> registerStatus, Integer customerPayment, Integer productCost) {
        var change = customerPayment - productCost;
        var bills = registerStatus.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        var sum = 0;
        for (Integer bill : bills) {
            var quantity = registerStatus.get(bill);
            if(quantity == 0 || bill > change) {
                continue;
            }
            var count = 1;
            while(count<= quantity) {
                sum += bill;
                if(sum == change) {
                    return true;
                }
                if(sum > change) {
                    sum-=bill;
                    break;
                }
                count++;
            }
        }
        return false;
    }

}
