package fr.natixis.shop.services;

import fr.natixis.shop.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ShopCartServiceImpl implements ShopCartService {
    public static final String SPACE = "           ";
    public static final String LINE_SEPARATOR = "----------------------------------------------------------------------";
    public static final String LINE_FORMAT = "%s %s %s %s %s %s %s\n";
	public static final String File_PATH = "c:/temp/result.json";
    static Logger LOGGER = LoggerFactory.getLogger(ShopCartServiceImpl.class);

    private final Map<Product, Integer> items = new HashMap<>();


    @Override
    public synchronized Map<Product, Integer> addProduct(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
        return items;
    }

    @Override
    public synchronized void calculateResult() {

        // format and Log result
        StringBuilder result = new StringBuilder()
                .append("\n")
                .append(getFormattedLine("Product", "Quantity", "Price", "Total"))
                .append(LINE_SEPARATOR)
                .append("\n");

        items.forEach((prod, qty) -> result.append(getFormattedLine(prod.getName(), qty, prod.getPrice(), prod.getPrice() * qty)));

        result.append(getFormattedLine("Total", SPACE, SPACE, calculateTotal()));
        LOGGER.info(result.toString());

        // generate json file
        File file = FileUtils.saveFile(File_PATH);
        FileUtils.writeJsonContent(file, items);

    }

    private static String getFormattedLine(Object... values) {
        if (values.length < 4) {
            throw new IllegalArgumentException("lines should have at least 4 dynamic variables");
        }

        return String.format(LINE_FORMAT, values[0], SPACE, values[1], SPACE, values[2], SPACE, values[3]);
    }

    private double calculateTotal() {
        return items.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                .sum();
    }
}
