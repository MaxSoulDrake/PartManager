package mygroup.partmanager.controller;

import mygroup.partmanager.model.Part;
import mygroup.partmanager.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PartController {
    private int page;
    private Boolean required;
    private String search;

    private PartService partService;

    @Autowired
    public void setPartService(PartService partService) {
        this.partService = partService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allParts(@RequestParam(required = false, defaultValue = "1") int page,
                                 @RequestParam(required = false) Boolean required,
                                 @RequestParam(required = false, defaultValue = "") String search) {
        List<Part> parts = partService.allParts(page, required, search);
        int partsCount = partService.partsCount(required, search);
        int allPartsCount = partService.allPartsCount();
        int computersCount = partService.computersCount();
        int pagesCount = (partsCount + 9)/10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("parts");
        modelAndView.addObject("page", page);
        modelAndView.addObject("required", required);
        modelAndView.addObject("search", search);
        modelAndView.addObject("parts", parts);
        modelAndView.addObject("partsCount", partsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("allPartsCount", allPartsCount);
        modelAndView.addObject("computersCount", computersCount);
        this.page = page;
        this.required = required;
        this.search = search;
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPart(@ModelAttribute("part") Part part) {
        ModelAndView modelAndView = new ModelAndView();
        if (partService.checkTitle(part.getTitle())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", page);
            if (required != null) modelAndView.addObject("required", required);
            if (!search.isEmpty()) modelAndView.addObject("search", search);
            partService.add(part);
        } else {
            modelAndView.addObject("message","part with this title already exists");
            modelAndView.setViewName("redirect:/add");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Part part = partService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("part", part);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editPart(@ModelAttribute("part") Part part) {
        ModelAndView modelAndView = new ModelAndView();
        if (partService.checkTitle(part.getTitle())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", page);
            if (required != null) modelAndView.addObject("required", required);
            if (!search.isEmpty()) modelAndView.addObject("search", search);
            partService.edit(part);
        } else {
            modelAndView.addObject("message","part with this title already exists");
            modelAndView.setViewName("redirect:/edit/" + part.getId());
        }
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletePart(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        int partsCount = partService.partsCount(required, search);
        int page = ((partsCount - 1) % 10 == 0 && partsCount > 10 && this.page == (partsCount + 9)/10) ?
                this.page - 1 : this.page;
        modelAndView.setViewName("redirect:/");
        modelAndView.addObject("page", page);
        if (required != null) modelAndView.addObject("required", required);
        if (!search.isEmpty()) modelAndView.addObject("search", search);
        Part part = partService.getById(id);
        partService.delete(part);
        return modelAndView;
    }
}
