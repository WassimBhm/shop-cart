package fr.natixis.shop;

import fr.natixis.shop.models.Product;
import fr.natixis.shop.services.ShopCartService;
import fr.natixis.shop.services.ShopCartServiceImpl;

public class App {

	static ShopCartService shopCartService = new ShopCartServiceImpl();

	public static void main(String[] args) {
		Product pen = Product.builder().id(1L).name("Pen").price(1.50).build();
		Product book = Product.builder().id(2L).name("Book").price(8.00).build();

		shopCartService.addProduct(pen, 3);
		shopCartService.addProduct(book, 2);
		shopCartService.calculateResult();
	}
}
