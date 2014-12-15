package org.tuxdevelop.spring_data_demo.nosql.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tuxdevelop.spring_data_demo.nosql.controller.model.AddModel;
import org.tuxdevelop.spring_data_demo.nosql.controller.model.TextViewModel;
import org.tuxdevelop.spring_data_demo.nosql.domain.Committer;
import org.tuxdevelop.spring_data_demo.nosql.domain.Text;
import org.tuxdevelop.spring_data_demo.nosql.repository.CommitterRepository;
import org.tuxdevelop.spring_data_demo.nosql.repository.TextRepository;

@Controller
public class TextController {

    @Autowired
    private TextRepository textRepository;

    @Autowired
    private CommitterRepository committerRepository;

    @Autowired
    private AddModel addModel;

    @Autowired
    private TextViewModel textViewModel;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String initRoot(final Model model){
        model.addAttribute("addModel",addModel);
        return "redirect:/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public AddModel initAdd(final Model model){
        model.addAttribute("addModel",addModel);
        return addModel;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addText(@ModelAttribute final AddModel addModel){
        final Committer committer = new Committer();
        committer.setName(addModel.getCommitter());
        committerRepository.save(committer);
        final Text text = new Text();
        text.setText(addModel.getText());
        text.setCommitter(committer);
        textRepository.save(text);
    }

    @RequestMapping(value = "/viewer",method = RequestMethod.GET)
    public TextViewModel getTextViews(final Model model){
        model.addAttribute("textViewModel",textViewModel);
        return textViewModel;
    }



}
