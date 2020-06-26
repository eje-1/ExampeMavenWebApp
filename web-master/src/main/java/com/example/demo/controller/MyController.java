package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.Connet;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import com.example.demo.model.PersonF;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class MyController {

    @Autowired(required = false)
    private UserService service;

    Connet connet;

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String index(Model model){

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
    public String personList(Model model) {

        Connet connetion = Connet.getInstance();
        model.addAttribute("users", connetion.getUsers());

        return "personList";
    }

    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        PersonF person = new PersonF();
        model.addAttribute("personF", person);

        return "addPerson";
    }

    @RequestMapping(value = "addPerson", method = RequestMethod.POST)
    public String savePerson(Model model,//
                             @ModelAttribute("personF")PersonF personF){
        String firstName = personF.getName();
        String lastName = personF.getVorname();
        String mail = personF.getEmail();
        String telefon = personF.getTelefon();
        String street = personF.getStrasse();
        String place = personF.getOrt();
        String plz = personF.getPlz();
        String sex = personF.getSex();
        String birthday = personF.getGeburtstag();
        String nick = personF.getSpitzname();

        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0) {
            User newPerson = new User(0,firstName,lastName,mail,telefon,street,place,plz,sex,birthday,nick);
            System.out.println("Add");

            Connet connetion = Connet.getInstance();

            if(connetion.saveUser(newPerson)){
                return "redirect:/personList";
            } else {
                System.out.println("Error");
            }
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id){

        Connet connetion = Connet.getInstance();
        connetion.delete(id);

        return "redirect:/personList";
    }

    @RequestMapping(value = { "/editPerson/update/{id}" }, method = RequestMethod.GET)
    public String showEditPersonPage(@PathVariable(name = "id") int id, Model model)  {

        Connet connetion = Connet.getInstance();
        model.addAttribute("personF", connetion.getId(id));


        return "editPerson";
    }


    @RequestMapping(value = "/editPerson/update/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("personF")PersonF personF) {

        Connet connetion = Connet.getInstance();

        int id = personF.getId();
        String firstName = personF.getName();
        String lastName = personF.getVorname();
        String mail = personF.getEmail();
        String telefon = personF.getTelefon();
        String street = personF.getStrasse();
        String place = personF.getOrt();
        String plz = personF.getPlz();
        String sex = personF.getSex();
        String birthday = personF.getGeburtstag();
        String nick = personF.getSpitzname();

        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0) {
            User editPerson = new User(id,firstName, lastName, mail, telefon, street, place, plz, sex, birthday, nick);
            System.out.println("Edit");

            if (connetion.update(editPerson)) {
                return "redirect:/personList";
            } else {
                System.out.println("Error");
            }
        }
        return "editPerson";
    }


    @RequestMapping("/personList")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<User> listOfUser = service.listAll(keyword);
        model.addAttribute("users", listOfUser);
        model.addAttribute("keyword", keyword);

        return "redirect:/personList";
    }

}