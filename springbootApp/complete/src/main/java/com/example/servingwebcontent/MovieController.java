package com.example.servingwebcontent;

import com.example.servingwebcontent.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {
    private List<Movie> movieList = new ArrayList<>();

    @GetMapping("/movies/add")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "add-movie";
    }

    @PostMapping("/movies/add")
    public String addMovie(@ModelAttribute Movie movie, Model model) {
        try {
            movieList.add(movie);
            model.addAttribute("message", "Đã thêm phim: " + movie.getTitle());
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        } finally {
            // Có thể log hoặc dọn dẹp tài nguyên nếu cần
        }
        model.addAttribute("movie", new Movie());
        return "add-movie";
    }

    @GetMapping("/movies")
    public String showMovieList(Model model) {
        model.addAttribute("movies", movieList);
        return "movies";
    }
}
