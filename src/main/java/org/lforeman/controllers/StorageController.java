package org.lforeman.controllers;

import org.lforeman.models.Storage;
import org.lforeman.models.data.StorageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("storage")
public class StorageController {

    @Autowired
    private StorageDao storageDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("storage", storageDao.findAll());
        model.addAttribute("title", "Storage");

        return "storage/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {

        model.addAttribute("title", " Add Storage");
        model.addAttribute("storage", new Storage());
        return "storage/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model,
                      @ModelAttribute @Valid Storage storage, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Storage");
            return "storage/add";
        }

        storageDao.save(storage);
        return "redirect:";
    }
}
