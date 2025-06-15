package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Movie;
import com.example.servingwebcontent.model.MovieList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieList movieList = new MovieList();

    @GetMapping
    public String listMovies(Model model) {
        model.addAttribute("movies", movieList.movies);
        return "movies";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("movie", new Movie("", "", "", "", 0, "", 0));
        return "add-movie";
    }

    @PostMapping("/add")
    public String addMovie(@ModelAttribute Movie movie) {
        movieList.addMovie(movie);
        return "redirect:/movies";
    }
}
