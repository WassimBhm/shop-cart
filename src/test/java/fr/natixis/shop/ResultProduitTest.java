package fr.natixis.shop;

import fr.natixis.shop.models.Product;
import fr.natixis.shop.services.ShopCartService;
import fr.natixis.shop.services.ShopCartServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultProduitTest {

	@Test
	public void addProduct_should_add_inexistant_product() {

		// GIVEN
		ShopCartService underTest = new ShopCartServiceImpl();
		Product pen = Product.builder().id(1L).name("Pen").price(1.50).build();
		int quantity = 3;

		// WHEN
		Map<Product, Integer> result = underTest.addProduct(pen, quantity);
		// THEN
		assertEquals(1, result.size(), "no item was added");
		assertEquals(quantity, result.get(pen), "quantity of added item is incorrect");
		assertEquals(result.keySet().contains(pen), "added item is different than expected one");

	}

	@Test
	public void addProduct_should_update_quantity_of_existant_product() {

		// GIVEN
		ShopCartService underTest = new ShopCartServiceImpl();

		Product pen = Product.builder().id(1L).name("Pen").price(1.50).build();
		int firstQuantity = 3;
		int secondQuantity = 2;

		Map<Product, Integer> result1 = underTest.addProduct(pen, firstQuantity);

		assertEquals(firstQuantity, result1.get(pen), "first quantity of added item is incorrect");

		Map<Product, Integer> result2 = underTest.addProduct(pen, secondQuantity);

		int expectedFinalQuantity = firstQuantity + secondQuantity;
		assertEquals(expectedFinalQuantity, result2.get(pen), "final quantity is incorrect");

	}

}
