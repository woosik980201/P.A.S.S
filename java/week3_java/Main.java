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
System.out.println("2. 상품 입고");
System.out.println("3. 상품 출고");
System.out.println("4. 상품 판매");
System.out.println("5. 상품 추가");
System.out.println("6. 상품 삭제");
System.out.println("7. 종료");
System.out.print("선택: ");
    
int choice = scanner.nextInt();
    if (choice == 1) {
       for(int i=0; i<products.size(); i++){
           System.out.println(
    (i + 1) + ". "
    + products.get(i).name
    + " / 가격: "
    + products.get(i).price
    + " / 재고: "
    + products.get(i).stock
);
        }
    }else if (choice == 2) {
        System.out.print("입고할 상품 번호를 입력하세요: ");
        int productNumber = scanner.nextInt();
        if (productNumber >= 1 && productNumber <= products.size()) {
            System.out.print("입고 수량을 입력하세요: ");
            int quantity = scanner.nextInt();
            Product selectedProduct = products.get(productNumber - 1);
            selectedProduct.addStock(quantity);
        } else {
            System.out.println("잘못된 상품 번호입니다.");
        }
    } else if (choice == 3) {
        System.out.print("출고할 상품 번호를 입력하세요: ");
        int productNumber = scanner.nextInt();
        if (productNumber >= 1 && productNumber <= products.size()) {
            System.out.print("출고 수량을 입력하세요: ");
            int quantity = scanner.nextInt();
            Product selectedProduct = products.get(productNumber - 1);
            selectedProduct.removeStock(quantity);
        } else {
            System.out.println("잘못된 상품 번호입니다.");
        }
    } else if (choice == 4) {
        System.out.print("판매할 상품 번호를 입력하세요: ");
        int productNumber = scanner.nextInt();
        if (productNumber >= 1 && productNumber <= products.size()) {
            Product selectedProduct = products.get(productNumber - 1);
            selectedProduct.sell();
        } else {
            System.out.println("잘못된 상품 번호입니다.");
        }
    } else if (choice == 5) {
        scanner.nextLine(); // Consume the newline character
        
        System.out.print("추가할 상품 이름을 입력하세요: ");
        String newName = scanner.nextLine();
        
        System.out.print("추가할 상품 가격을 입력하세요: ");
        int newPrice = scanner.nextInt();
        
        System.out.print("추가할 상품 재고를 입력하세요: ");
        int newStock = scanner.nextInt();
        
        products.add(new Product(newName, newPrice, newStock));
        System.out.println("상품이 추가되었습니다.");
    } else if (choice == 6) {
        System.out.print("삭제할 상품 번호를 입력하세요: ");
        int productNumber = scanner.nextInt();
        if (productNumber >= 1 && productNumber <= products.size()) {
            products.remove(productNumber - 1);
            System.out.println("상품이 삭제되었습니다.");
        } else {
            System.out.println("잘못된 상품 번호입니다.");
        }
    } else if (choice == 7) {
        System.out.println("프로그램을 종료합니다.");
        break;
    } else {
        System.out.println("잘못된 선택입니다.");
       
    }
}
 scanner.close();
    }
}
