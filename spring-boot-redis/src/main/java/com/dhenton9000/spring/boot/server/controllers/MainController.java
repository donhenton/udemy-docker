/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.spring.boot.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

/**
 *
 * @author dhenton
 */
@Controller
public class MainController {

    @Autowired
    private Jedis jedisClient;
    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);
    private static final String HASH_NAMESPACE = "stuff";

    @RequestMapping("/")
    public ModelAndView homePage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/home");
        mv.addObject("key", "");
        mv.addObject("cacheValue", "");
        mv.addObject("actionMessage", "");
        return mv;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String keyRequest(@RequestParam("submit") String submitValue,
            @RequestParam("key") String key, @RequestParam("cacheValue") String value,
            Model model) {

        String actionMessage = "";
        String foundThing = jedisClient.hget(HASH_NAMESPACE, key);
        LOG.debug("key is (" + key + ")");
        if (key != null && !key.isEmpty()) {

            if (submitValue.equals("load")) {
                actionMessage = "Looked for (" + key + ")  and loaded  --> " + foundThing + " (ignored submitted value)";
            } else {

                if (value != null & !value.isEmpty()) {
                    actionMessage = "submitted key " + key + " --> " + value;
                    this.jedisClient.hset(HASH_NAMESPACE, key, value);
                }

            }
        }
        model.addAttribute("key", "");
        model.addAttribute("cacheValue", "");
        model.addAttribute("actionMessage", actionMessage);
        return "pages/home";
    }

}
