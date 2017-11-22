package com.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchServiceImpl implements SearchService {

	public static void main(String[] args) {
		try {
			ServiceFactory sFactory = new ServiceFactory();
			SearchService service = sFactory.getSearchService();
			List<String> resultString = service.findFiles(pathName, arrayWord);
			System.out.println("return List : "+ resultString);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch(InvalidPathException ipe){
			System.out.println("Path does not exist");
		}
	}
	
	@Override
	public List<String> findFiles (String pathName, String [] arrayWord) throws IOException{
		Path dirPath = Paths.get(pathName);
		List <String> resultFiles = new ArrayList<>();

		BiPredicate<Path, BasicFileAttributes> findBP = (path, attributes) -> path.toFile().isFile();
		try (Stream<Path> fileStream = Files.find(dirPath, 10, findBP)) {
			List<Path> files = fileStream.collect(Collectors.toList());
			for(Path filePath:files){
				processFiles(arrayWord, resultFiles, filePath);
			}
		}

		List<String> fileName = resultFiles.stream().sorted().map(String::valueOf).collect(Collectors.toList());
		return fileName;
	}

	private static void processFiles(String[] arrayWord, List<String> resultFiles, Path filePath) {
		File file = filePath.toFile();
		boolean found = true;

		try (Stream<String> stream = Files.lines(filePath)) {
			String fileContent = stream.map(String::valueOf).map(str -> str+" ").collect(Collectors.joining());
			for (String sWord : arrayWord){
				if (!fileContent.contains(sWord)){
					found =false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(found){
			resultFiles.add(file.getName());
		}
	}
	
}
