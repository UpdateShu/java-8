package com.geekbrains.person.salesman;

import com.geekbrains.person.Person;
import com.geekbrains.person.customer.Customer;
import com.geekbrains.product.Product;

import java.util.List;

public class Salesman extends Person {
    private String name;
    private String secondName;
    private List<Product> products;

    public boolean sellProducts(Customer customer, Product wannaBuy) {
        for(Product product: products) {
            // Проверяем по имени что у продавца есть продукт
            if(product.getName().equals(wannaBuy.getName())) {
                //Проверяем что его количество >= чем мы хотим купить
                if(product.getCount() >= wannaBuy.getCount()) {
                    //Проверяем что кэш покупателя позволяет купить товар
                    long requiredCash = (long) product.getPrice() * wannaBuy.getCount();
                    if(customer.getCash() >= requiredCash) {
                        //Уменьшаем количество продуктов у продавца
                        product.setCount(product.getCount() - wannaBuy.getCount());

                        //Создаем новый объект продуктов, чтобы создать новую ссылку
                        Product customerProduct = new Product();
                        customerProduct.setCount(wannaBuy.getCount());
                        customerProduct.setName(wannaBuy.getName());

                        //Добавляем количество продуктов у покупателя
                        customer.addPurchase(customerProduct);
                        //Увеличеваем кэш продавца
                        this.setCash((int) (this.getCash() + requiredCash));
                        //Уменьшаем кэш покупателя
                        customer.setCash((int) (customer.getCash() - requiredCash));
                        //Выходим из метода
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
