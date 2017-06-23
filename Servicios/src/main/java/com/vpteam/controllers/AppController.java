package com.vpteam.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vpteam.entities.Job;

@Controller
public class AppController 
{	
    List<Job> listaPersona = new ArrayList<>();
    final static Logger logger = Logger.getLogger(AppController.class);
    @RequestMapping(value="/login")
    public String login()
    {	
        return "login";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response)
    {	
        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        session= request.getSession(false);
        
        if(session != null) 
        {
            session.invalidate();
        }
    
        for(Cookie cookie : request.getCookies()) 
        {
            cookie.setMaxAge(0);
        }
        
        return "login";
    }

    @RequestMapping(value="/new_job")
    public String createNewJob(Model model)
    {
        model.addAttribute("job", new Job());
        return "new_job";
    }

    @RequestMapping(value="/insertarPersona", method=RequestMethod.POST)
    public String storeData(@ModelAttribute Job job, Model model)
    {
        return "redirect:/dashboard";
    }
        
    @RequestMapping(value="/dashboard_oferente")
    public String dashboardOferente(Model model)
    {

        return "dashboard_oferente";
    }

    @RequestMapping(value="/dashboard")
    public String dashboard(Model model)
    {

        return "dashboard";
    }

    @RequestMapping(value="/insertarPersona")
    public String storeData(Model model)
    {	
        model.addAttribute("persona", new Job());
        return "nueva_persona";
    }

    /*@RequestMapping(value="/insertarPersona", method=RequestMethod.POST)
    public String storeData(@ModelAttribute Job persona, Model model)
    {

        return "redirect:/dashboard";
    }*/
	
  }
