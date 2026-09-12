# Java 학습 기록

문법을 따로 외우기보다, 작은 프로그램을 직접 만들면서 개념을 이해하는 걸 목표로 하고 있다.
이 폴더에는 그 과정에서 만든 코드와 정리한 내용을 남긴다.

- `product-management/` : 최신 버전 (아래 정리된 내용 기준)
- `week2_java/` : 2주차 시점 스냅샷 (조회/판매/종료)
- `week3_java/` : 3주차 시점 스냅샷 (입고/출고/추가/삭제 반영)

## Class & Object

먼저 "클래스가 왜 필요한가"부터 시작했다. 상품 하나를 다루려면 이름, 가격, 재고 같은 정보가 같이 묶여서 움직여야 하는데, 이걸 변수 여러 개로 따로따로 관리하면 상품이 늘어날 때마다 감당이 안 된다. 그래서 이름/가격/재고를 하나로 묶고, 그 데이터를 다루는 동작(판매 등)까지 같이 넣어둔 게 클래스라는 걸 실습하면서 체감했다.

`Product` 클래스는 이렇게 생겼다.

```java
public class Product {
    String name;
    int price;
    int stock;

    Product(String name, int price, int stock){
    this.name = name;
    this.price = price;
    this.stock = stock;
    }
```

필드는 `name`, `price`, `stock` 세 개. 생성자에서 매개변수 이름을 필드 이름과 똑같이 `name`, `price`, `stock`으로 뒀는데, 그러면 `this.name`처럼 `this`를 붙여야 "지금 이 객체의 필드"라는 뜻이 된다. `this`를 안 붙이면 매개변수 자기 자신한테 대입하는 꼴이라 아무 의미가 없어진다는 걸 여기서 이해했다.

`new Product("Erl hoodie", 450000, 3)` 이렇게 만들면 객체가 하나 생기고, 그걸 담는 게 참조 변수다. 변수 자체에 객체가 들어있는 게 아니라 객체를 "가리키고" 있다는 개념인데, 이건 뒤에 ArrayList 넘어가면서 더 확실히 와닿았다.

객체 상태를 바꾸는 부분은 `sell()`이다.

```java
public void sell(){
   if(stock > 0){
        stock--;
    System.out.println(name + " 판매완료!");
    System.out.println("남은 재고: " + stock);
   }else {
    System.out.println("재고가 없습니다.");
   }
    System.out.println();
}
```

`sell()`을 호출하면 그 객체의 `stock`만 줄어든다. 다른 상품 객체의 재고는 전혀 영향을 안 받는다. 즉 객체마다 자기 상태를 독립적으로 가지고 있다는 걸 여기서 확인했다 — 같은 클래스로 찍어낸 객체라도 각자 다른 값을 들고 다니는 것.

`showInfo()`도 만들어뒀는데 지금 미니 프로젝트 메뉴에서는 안 쓰고 있다. 나중에 상품 상세보기 같은 메뉴를 추가하면 쓸 자리로 남겨둔 것.

## ArrayList

클래스/객체까지는 좋았는데, 상품이 여러 개면 문제가 생겼다. 처음엔 이렇게 할 생각이었다.

```java
Product product1 = new Product("Erl hoodie", 450000, 3);
Product product2 = new Product("Diesel Jeans", 320000, 5);
Product product3 = new Product("Our Legacy Shirt", 450000, 2);
```

이러면 상품이 4개, 5개로 늘어날 때마다 변수를 계속 새로 만들어야 하고, 반복문으로 전체를 돌리는 것도 안 된다. `product1`, `product2`... 이런 식으로는 몇 개인지 모르는 상품 목록을 다룰 수가 없다.

그래서 `ArrayList<Product>`로 바꿨다.

```java
ArrayList<Product> products = new ArrayList<>();

products.add(new Product("Erl hoodie", 450000, 3));
products.add(new Product("Diesel Jeans", 320000, 5));
products.add(new Product("Our Legacy Shirt", 450000, 2));
products.add(new Product("Stussy Jacket", 280000, 4));
```

`products` 하나에 객체를 계속 `add()`로 넣기만 하면 되고, 몇 개가 들어있는지는 `products.size()`로 알 수 있다. 개별 객체는 `products.get(i)`로 꺼낸다. 참조 변수를 하나하나 만드는 대신, ArrayList 안에 참조들을 순서대로 담아두는 구조라는 걸 여기서 이해했다 — ArrayList가 객체 자체를 복사해서 갖고 있는 게 아니라, 각 객체를 가리키는 참조를 리스트로 들고 있는 것.

이렇게 하니까 for문이랑 바로 붙는다.

```java
for(int i=0; i<products.size(); i++){
    System.out.println((i+1) + ". " + products.get(i).name);
}
```

여기서 헷갈렸던 부분이 상품 번호랑 index 차이다. `products.get(i)`의 `i`는 0부터 시작하는데, 사용자한테 보여줄 때 "1번 상품"부터 보여줘야 자연스러우니까 출력은 `i+1`로 한다. 반대로 사용자가 "1번 상품 판매해주세요"라고 번호를 입력하면, 그 번호로 리스트에 접근할 때는 `choice - 1`을 해줘야 실제 index랑 맞는다.

```java
int productNumber = scanner.nextInt();
if (productNumber >= 1 && productNumber <= products.size()) {
    Product selectedProduct = products.get(productNumber - 1);
    selectedProduct.sell();
}
```

이 1↔0 변환을 매번 신경 써야 한다는 걸 실습하면서 확실히 익혔다.

## Mini Project — 상품 관리 프로그램

전체 조회 / 판매 / 종료, 세 가지 메뉴를 반복해서 보여주는 콘솔 프로그램이다. `while(true)`로 무한 반복시키고, 종료를 선택하면 `break`로 빠져나온다.

```java
while (true) {
System.out.println("===== 상품 관리 프로그램 =====");
System.out.println("1. 전체 상품 조회");
System.out.println("2. 상품 판매");
System.out.println("3. 종료");
System.out.print("선택: ");

int choice = scanner.nextInt();
    if (choice == 1) {
       ...
    }else if (choice == 2) {
       ...
    } else if (choice == 3) {
        System.out.println("프로그램을 종료합니다.");
        break;
    } else {
        System.out.println("잘못된 선택입니다.");
    }
}
```

1번을 고르면 위에서 만든 for문으로 상품 목록이 이름과 번호로 쭉 나오고, 2번을 고르면 번호를 입력받아서 그 상품의 `sell()`을 호출한다. 범위를 벗어난 번호를 입력하면 "잘못된 상품 번호입니다" 메시지가 나오도록 처리했다. 3번을 고르면 안내 메시지를 찍고 `break`로 while문을 빠져나가면서 프로그램이 끝난다.

## 입고 / 출고 / 상품 추가·삭제

판매(`sell()`)만 있을 때는 재고가 줄어드는 방향으로만 움직였는데, 실제 상품 관리라면 물건이 들어오는 경우(입고)도 다뤄야 한다는 생각이 들어서 `addStock(quantity)`와 `removeStock(quantity)`를 따로 만들었다.

```java
public void addStock(int quantity) {
    if (quantity > 0) {
        stock += quantity;
        System.out.println("입고 완료!");
        System.out.println("현재 재고: " + stock);
    } else {
        System.out.println("입고 수량은 1개 이상이어야 합니다.");
    }
}

public void removeStock(int quantity) {
    if (quantity <= 0) {
        System.out.println("출고 수량은 1개 이상이어야 합니다.");
    } else if (stock >= quantity) {
        stock -= quantity;
        System.out.println("출고 완료!");
        System.out.println("현재 재고: " + stock);
    } else {
        System.out.println("재고가 부족합니다.");
    }
}
```

`sell()`은 한 번에 1개씩만 줄이면 됐지만, 입고/출고는 수량을 입력받기 때문에 그 수량이 유효한지 먼저 걸러야 했다. 특히 출고는 `stock >= quantity`를 확인해서 재고보다 많이 빼내려는 요청을 막아야 한다는 걸 여기서 신경 쓰게 됐다.

메뉴도 조회/판매/종료 3개에서 조회·입고·출고·판매·추가·삭제·종료 7개로 늘어났다. 상품 추가/삭제는 `ArrayList`가 가진 `add()`/`remove(index)`를 그대로 쓰면 되는데, 상품 삭제도 판매·입고·출고처럼 "몇 번 상품"으로 입력받으니까 `products.remove(productNumber - 1)`로 1↔0 인덱스 변환을 또 챙겨야 했다.

```java
} else if (choice == 5) {
    scanner.nextLine(); // 이전 nextInt()가 남긴 개행 문자 제거
    System.out.print("추가할 상품 이름을 입력하세요: ");
    String newName = scanner.nextLine();
    ...
    products.add(new Product(newName, newPrice, newStock));
}
```

상품 추가에서 이름은 `Scanner.nextLine()`으로 받아야 하는데, 직전에 `choice`를 `nextInt()`로 읽으면 입력 버퍼에 개행 문자가 남아있어서 이름 입력을 건너뛰어 버리는 문제를 겪었다. `nextInt()` 다음에 `nextLine()`을 한 번 더 호출해서 남은 개행을 비워줘야 한다는 걸 이번에 직접 겪으면서 알게 됐다.

## 실행 방법

`product-management` 폴더에서:

```
javac Main.java
java Main
```
