package fr.natixis.shop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class Product {

	private Long id;
	private String name;
	private double price;

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}

		if (!(o instanceof Product)) {
			return false;
		}

		Product p = (Product) o;

		return id != null && id.equals(p.getId()) || id == null && p.getId() == null;
	}
}
