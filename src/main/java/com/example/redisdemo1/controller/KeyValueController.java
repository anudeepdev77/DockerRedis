package com.example.redisdemo1.controller;

import com.example.redisdemo1.service.KeyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keyvalue")
public class KeyValueController {
    @Autowired
    private KeyValueService keyValueService;


    @PostMapping("/add")
    public String saveKeyValue(@RequestParam String key, @RequestParam String value) {
        keyValueService.saveValue(key, value);
        return "Saved key-value pair!";
    }

    @GetMapping("/{key}")
    public String getValue(@PathVariable String key) {
        return keyValueService.getValue(key);
    }

    @PutMapping("/update")
    public String updateValue(@RequestParam String key, @RequestParam String value) {
        keyValueService.updateValue(key, value);
        return "Updated key-value pair!";
    }

    @DeleteMapping("/delete/{key}")
    public String deleteValue(@PathVariable String key) {
        keyValueService.deleteValue(key);
        return "Deleted key-value pair!";
    }
}
