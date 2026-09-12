    public class Product {
    String name;
    int price;
    int stock;

    Product(String name, int price, int stock){
    this.name = name;
    this.price = price;
    this.stock = stock;
    }

    public void showInfo() {
        System.out.println("Product Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Stock: " + stock);
    }
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
}
