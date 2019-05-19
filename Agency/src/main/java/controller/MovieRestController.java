package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.MovieList;
import service.MovieServiceImpl;

@RestController
@RequestMapping("/movies")
public class MovieRestController {
@Autowired
private MovieServiceImpl movieService;
	@GetMapping("/getList")
	public List<MovieList> getMovies() {
		return movieService.getMovies();
	}
	

}
