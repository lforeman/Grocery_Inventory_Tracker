package org.lforeman.controllers;

import org.lforeman.models.Storage;
import org.lforeman.models.Grocery;
import org.lforeman.models.data.StorageDao;
import org.lforeman.models.data.GroceryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("grocery")
public class GroceryController {
    @Autowired
    GroceryDao groceryDao;

    @Autowired
    StorageDao storageDao;

    // Request path: /grocery
    //TODO figure out toString display

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("groceries", groceryDao.findAll());
        model.addAttribute("title", "My Groceries");

        return "grocery/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddGroceryForm(Model model) {
        model.addAttribute("title", "Add Groceries");
        model.addAttribute(new Grocery());
        model.addAttribute("storage", storageDao.findAll());
        return "grocery/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddGroceryForm(@ModelAttribute  @Valid Grocery newGrocery,
                                       Errors errors, @RequestParam int storageId, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Groceries");
            model.addAttribute(newGrocery);
            model.addAttribute("storage", storageDao.findAll());
            return "grocery/add";
        }

        Storage cat = storageDao.findOne(storageId);
        newGrocery.setStorage(cat);
        groceryDao.save(newGrocery);
        return "redirect:";
    }
//TODO: designate user before removing

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveGroceryForm(Model model) {
        model.addAttribute("groceries", groceryDao.findAll());
        model.addAttribute("title", "Remove Grocery");
        return "grocery/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveGroceryForm(@RequestParam int[] groceryIds) {

        for (int id : groceryIds) {
            groceryDao.delete(id);
        }

        return "redirect:";
    }
//TODO designate user to storage

    @RequestMapping(value = "storage", method = RequestMethod.GET)
    public String storage(Model model, @RequestParam int id) {
        Storage cat = storageDao.findOne(id);
        List<Grocery> groceries = cat.getGroceries();
        model.addAttribute("groceries", groceries);
        model.addAttribute("title", "Groceries in Storage; " + cat.getName());
        return "grocery/index";
    }

}
