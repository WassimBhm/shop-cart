package fr.natixis.shop.services;

import fr.natixis.shop.models.Product;

import java.util.Map;

public interface ShopCartService {

	Map<Product, Integer> addProduct(Product product, int quantity);

	void calculateResult();

}
