package fr.on.mange.quoi.recipe.ismail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class  CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/add")
    public String categoryListForm(Category category) {
        return "add";
    }

    @PostMapping("/add")
    public String addCategory(@Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "category";
        }

        categoryRepository.save(category);
        return "redirect:/category";
    }


    @GetMapping("/category")
    public String categoryList(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "category";
    }
    @GetMapping("/edit/{id}")
    public String updateCategory(@PathVariable("id") long id, Model model) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("category", category);
        return "update-category";
    }
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") long id, @Valid Category category,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            category.setId(id);
            return "category";
        }

        categoryRepository.save(category);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") long id, Model model) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        categoryRepository.delete(category);
        return "redirect:/category";
    }

        // additional CRUD methods

}
