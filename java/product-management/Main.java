import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product("Erl hoodie", 450000, 3));
        products.add(new Product("Diesel Jeans", 320000, 5));
        products.add(new Product("Our Legacy Shirt", 450000, 2));
        products.add(new Product("Stussy Jacket", 280000, 4));


while (true) {
System.out.println("===== 상품 관리 프로그램 =====");
System.out.println("1. 전체 상품 조회");
System.out.println("2. 상품 판매");
System.out.println("3. 종료");
System.out.print("선택: ");
    
int choice = scanner.nextInt();
    if (choice == 1) {
       for(int i=0; i<products.size(); i++){
            System.out.println((i+1) + ". " + products.get(i).name);
        }
    }else if (choice == 2) {
        System.out.print("판매할 상품 번호를 입력하세요: ");
        int productNumber = scanner.nextInt();
        if (productNumber >= 1 && productNumber <= products.size()) {
            Product selectedProduct = products.get(productNumber - 1);
            selectedProduct.sell();
        } else {
            System.out.println("잘못된 상품 번호입니다.");
        }
    } else if (choice == 3) {
        System.out.println("프로그램을 종료합니다.");
        break;
    } else {
        System.out.println("잘못된 선택입니다.");
       
    }
}
 scanner.close();
    }
}