package networks.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import networks.exceptions.FriendShippingException;

public class FileUtil {

	public static List<String> getCSVFilesInDirectory(String pLocation) throws FriendShippingException {
		try (Stream<Path> lWalk = Files.walk(Paths.get(pLocation))) {

			List<String> lResult = lWalk.map(x -> x.toString()).filter(p -> p.endsWith(Constants.INPUT_FILE_EXTENSION))
					.collect(Collectors.toList());

			return lResult;
		} catch (IOException pException) {
			throw new FriendShippingException("File not found.");
		}
	}
}
