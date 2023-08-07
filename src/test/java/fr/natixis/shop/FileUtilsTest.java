package fr.natixis.shop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.natixis.shop.services.FileUtils;

@RunWith(MockitoJUnitRunner.class)
public class FileUtilsTest {

	@InjectMocks
	private FileUtils fileUtils;

	@Mock
	private ObjectMapper objectMapper;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		;
	}

	@Test
	public void testSaveFile() {
		String path = "c:\\temp\\result.json";
		File file = FileUtils.saveFile(path);
		assertNotNull(file);
		assertEquals(path, file.getPath());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveFileWithEmptyPath() {
		String path = "";
		FileUtils.saveFile(path);
	}
}
