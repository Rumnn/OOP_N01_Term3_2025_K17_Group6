package com.example.servingwebcontent;

import com.example.servingwebcontent.model.*;
import com.example.servingwebcontent.database.MovieAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {
    private MovieAiven movieAiven = new MovieAiven();

    // Hiển thị danh sách phim
    @GetMapping("/movies")
    public String listMovies(Model model) {
        try {
            System.out.println("🔍 MovieController: Starting to load movies...");
            List<Movie> movies = movieAiven.movieList();
            System.out.println("✅ MovieController: Successfully loaded " + movies.size() + " movies");
            for (Movie m : movies) {
                System.out.println("  - ID: " + m.getId() + ", Name: " + m.getName() + ", Title: " + m.getTitle());
            }
            model.addAttribute("movies", movies);
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong MovieController.listMovies: " + e.getMessage());
            System.out.println("  Error Type: " + e.getClass().getSimpleName());
            e.printStackTrace();
            model.addAttribute("error", "❌ Lỗi khi lấy danh sách phim: " + e.getMessage());
            model.addAttribute("movies", new ArrayList<>());
        }
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
            // Validate input
            if (movie.getId() == null || movie.getId().trim().isEmpty()) {
                model.addAttribute("message", "❌ Lỗi: ID phim không được để trống");
                model.addAttribute("movie", movie);
                return "add-movie";
            }
            if (movie.getName() == null || movie.getName().trim().isEmpty()) {
                model.addAttribute("message", "❌ Lỗi: Tên phim không được để trống");
                model.addAttribute("movie", movie);
                return "add-movie";
            }
            if (movie.getTitle() == null || movie.getTitle().trim().isEmpty()) {
                model.addAttribute("message", "❌ Lỗi: Tiêu đề không được để trống");
                model.addAttribute("movie", movie);
                return "add-movie";
            }
            
            System.out.println("🔍 Adding movie: " + movie.getId() + " - " + movie.getName());
            movieAiven.insertMovie(movie);
            model.addAttribute("message", "✅ Đã thêm phim thành công: " + movie.getTitle());
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong MovieController.addMovie: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("message", "❌ Lỗi khi thêm phim: " + e.getMessage());
        }
        model.addAttribute("movie", new Movie());
        return "add-movie";
    }

    // Hiển thị form sửa phim
    @GetMapping("/movies/edit/{id:.+}")
    public String editMovieForm(@PathVariable("id") String id, Model model) {
        try {
            System.out.println("🔍 Looking for movie with ID: " + id);
            List<Movie> movies = movieAiven.movieList();
            Movie movieToEdit = null;
            
            for (Movie m : movies) {
                System.out.println("  - Checking movie: " + m.getId() + " vs " + id);
                if (m.getId() != null && m.getId().equals(id)) {
                    movieToEdit = m;
                    break;
                }
            }
            
            if (movieToEdit != null) {
                System.out.println("✅ Found movie to edit: " + movieToEdit.getName());
                model.addAttribute("movie", movieToEdit);
                return "edit-movie";
            } else {
                System.out.println("❌ Movie not found with ID: " + id);
                model.addAttribute("message", "❌ Không tìm thấy phim với ID: " + id);
                model.addAttribute("movies", movies);
                return "movie-list";
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong MovieController.editMovieForm: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("message", "❌ Lỗi khi tìm phim: " + e.getMessage());
            return "movie-list";
        }
    }

    // Xử lý cập nhật phim
    @PostMapping("/movies/edit/{id:.+}")
    public String editMovie(@PathVariable String id, @ModelAttribute Movie movie, Model model) {
        try {
            // Validate input
            if (movie.getName() == null || movie.getName().trim().isEmpty()) {
                model.addAttribute("message", "❌ Lỗi: Tên phim không được để trống");
                model.addAttribute("movie", movie);
                return "edit-movie";
            }
            if (movie.getTitle() == null || movie.getTitle().trim().isEmpty()) {
                model.addAttribute("message", "❌ Lỗi: Tiêu đề không được để trống");
                model.addAttribute("movie", movie);
                return "edit-movie";
            }
            
            // Đảm bảo movie có đúng ID từ URL path
            movie.setId(id);
            movie.setOriginalId(id);
            
            System.out.println("🔍 Updating movie with ID: " + id);
            System.out.println("🔍 Movie data: " + movie.getName() + ", " + movie.getTitle());
            
            movieAiven.updateMovie(movie);
            model.addAttribute("message", "✅ Đã cập nhật phim thành công: " + movie.getName());
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong MovieController.editMovie: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("message", "❌ Lỗi khi cập nhật: " + e.getMessage());
            model.addAttribute("movie", movie);
            return "edit-movie";
        }
        List<Movie> movies = movieAiven.movieList();
        model.addAttribute("movies", movies);
        return "movie-list";
    }

    // Xóa phim
    @GetMapping("/movies/delete/{id:.+}")
    public String deleteMovie(@PathVariable String id, Model model) {
        try {
            System.out.println("🔍 Deleting movie with ID: " + id);
            movieAiven.deleteMovie(id);
            model.addAttribute("message", "✅ Đã xóa phim thành công!");
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong MovieController.deleteMovie: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("message", "❌ Lỗi khi xóa: " + e.getMessage());
        }
        List<Movie> movies = movieAiven.movieList();
        model.addAttribute("movies", movies);
        return "movie-list";
    }
}