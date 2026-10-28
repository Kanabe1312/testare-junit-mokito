package app.products.model;

public class Product {
    private int id;
    private String name;
    private int price;  //lei
    private int weight;  //gr/ml
    private String category;
    private int stock; //buc


    //Constructor
    public Product(int id, String name, int price, int weight, String category, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.category = category;
        this.stock = stock;
    }

    //Constructor cu string
    public Product(String text){
        String [] tokens = text.split(",");
       this.id = Integer.parseInt(tokens[0]);
       this.name = tokens[1];
       this.price = Integer.parseInt(tokens[2]);
       this.weight = Integer.parseInt(tokens[3]);
       this.category = tokens[4];
       this.stock = Integer.parseInt(tokens[5]);
    }

    private Product (ProductBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.weight = builder.weight;
        this.category = builder.category;
        this.stock = builder.stock;
    }

    public static class ProductBuilder {
        private int id;
        private String name;
        private int price;  //lei
        private int weight;  //gr/ml
        private String category;
        private int stock; //buc

        public ProductBuilder id(int id){
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name){
            this.name = name;
            return this;
        }

        public ProductBuilder price(int price){
            this.price = price;
            return this;
        }

        public ProductBuilder weight(int weight){
            this.weight = weight;
            return this;
        }

        public ProductBuilder category(String category){
            this.category = category;
            return this;
        }

        public ProductBuilder stock(int stock){
            this.stock = stock;
            return this;
        }

        public Product build() {
            return new Product(this);
        }

    }

    //Setters
    public void setId(int id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setPrice(int price){
        this.price = price;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setStock(int stock){
        this.stock = stock;
    }

    //Getters
    public int getId(){ return id; }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public int getWeight(){
        return weight;
    }
    public String getCategory(){
        return category;
    }
    public int getStock(){
        return stock;
    }

    @Override
    public String toString(){
        return this.id + "," + this.name + "," + this.price + "," + this.weight + "," + this.category + "," + this.stock;
    }






}
