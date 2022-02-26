package FEB2022.core2.singlton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
    }

    public static void main(String[] args) {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
