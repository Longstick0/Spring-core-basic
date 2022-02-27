package FEB2022.core2.singlton;

public class StatefulService {

//    private int price; //상태를 유지하는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
//        this.price = price; //여기가 문제!
        return price;
    }
}
