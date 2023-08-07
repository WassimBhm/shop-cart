package fr.natixis.shop.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.natixis.shop.models.Product;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileUtils {

	public static File saveFile(String path) {
		if (StringUtils.isEmpty(path)) {
			throw new IllegalArgumentException("path cannot be empty");
		}

		return new File(path);
	}

	public static void writeJsonContent(File file, Map<Product, Integer> content) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(file, content);
		} catch (IOException e) {
			throw new RuntimeException("Unable to write gson content");
		}
	}

}
