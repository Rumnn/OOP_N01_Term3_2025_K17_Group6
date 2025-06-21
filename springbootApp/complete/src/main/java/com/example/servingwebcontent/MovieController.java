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

    // Hiển thị danh sách phim
    @GetMapping("/movies")
    public String listMovies(Model model) {
        model.addAttribute("movies", movieList);
        return "movie-list";
    }

    // Hiển thị form thêm phim
    @GetMapping("/movies/add")
    public String addMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "add-movie";
    }

    // Xử lý thêm phim
    @PostMapping("/movies/add")
    public String addMovie(@ModelAttribute Movie movie, Model model) {
        try {
            movieList.add(movie);
            model.addAttribute("message", "Đã thêm phim: " + movie.getTitle());
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        model.addAttribute("movie", new Movie());
        return "add-movie";
    }

    // Hiển thị form sửa phim
    @GetMapping("/movies/edit/{id:.+}")
    public String editMovieForm(@PathVariable("id") String id, Model model) {
        Movie movieToEdit = null;
        for (Movie m : movieList) {
            if (m.getId().equals(id)) {
                movieToEdit = m;
                break;
            }
        }
        if (movieToEdit != null) {
            model.addAttribute("movie", movieToEdit);
            return "edit-movie";
        } else {
            model.addAttribute("message", "Không tìm thấy phim để sửa.");
            model.addAttribute("movies", movieList);
            return "movie-list";
        }
    }

    // Xử lý cập nhật phim
    @PostMapping("/movies/edit/{id}")
    public String editMovie(@PathVariable String id, @ModelAttribute Movie movie, Model model) {
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getId().equals(id)) {
                movieList.set(i, movie);
                model.addAttribute("message", "Đã cập nhật phim thành công!");
                break;
            }
        }
        model.addAttribute("movies", movieList);
        return "movie-list";
    }

    // Xóa phim
    @GetMapping("/movies/delete/{name}")
    public String deleteMovie(@PathVariable String name, Model model) {
        movieList.removeIf(m -> m.getName().equals(name));
        model.addAttribute("movies", movieList);
        model.addAttribute("message", "Đã xóa phim thành công!");
        return "movie-list";
    }

}