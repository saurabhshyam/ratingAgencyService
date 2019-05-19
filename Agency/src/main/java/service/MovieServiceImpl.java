package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import model.MovieList;

@Service
public class MovieServiceImpl {

	public List<MovieList> getMovies() {
		List<MovieList> list = new ArrayList<>();
		JSONParser jsonParser = new JSONParser();
		File file = getFileFromResources("assets.json");
		try (FileReader reader = new FileReader(file)) {
			// Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONArray movieList = (JSONArray) obj;

			// Iterate over movie array
			for (Object arr : movieList) {
				MovieList movies = new MovieList();
				movies = parseMovieObject((JSONObject) arr);
				if (null != movies)
					list.add(movies);
			}

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("file is not found!");
		} catch (IOException e) {
			throw new IllegalArgumentException("I/O Exception");
		} catch (ParseException e) {
			throw new IllegalArgumentException("ParseException occured");
		}
		sortListbyAge(list);
		return list;
	}

	private void sortListbyAge(List<MovieList> list) {
		if (!list.isEmpty()) {
			list.sort((MovieList m1, MovieList m2) -> m1.getYear().intValue() - m2.getYear().intValue());
		}
	}

	private File getFileFromResources(String fileName) {

		ClassLoader classLoader = getClass().getClassLoader();

		URL resource = classLoader.getResource(fileName);
		if (null == resource) {
			throw new IllegalArgumentException("file is not found!");
		} else {
			return new File(resource.getFile());
		}

	}

	private MovieList parseMovieObject(JSONObject movie) {

		String objectclass = (String) movie.get("object_class");

		if ("Movie".equalsIgnoreCase(objectclass)) {

			Long assetId = (Long) movie.get("asset_id");
			String title = (String) movie.get("title");
			Long year = (Long) movie.get("production_year");
			return new MovieList(assetId, title, year);
		}
		return null;
	}

}
