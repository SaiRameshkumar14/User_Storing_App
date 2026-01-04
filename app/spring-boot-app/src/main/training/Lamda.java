package training;

interface MyFunctionType {
    void display();
}

public class Lamda {
    public static void main(String[] args) {
       MyFunctionType a = () -> System.out.println("Hello");
    }
}
