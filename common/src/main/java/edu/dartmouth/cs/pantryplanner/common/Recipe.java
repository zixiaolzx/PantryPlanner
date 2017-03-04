package edu.dartmouth.cs.pantryplanner.common;

import com.google.gson.Gson;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by yangxk15 on 2/27/17.
 */

@AllArgsConstructor
@Data
public class Recipe {
    String name;
    Map<Item, Integer> items;
    List<String> steps;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public static Recipe fromString(String s) {
        return new Gson().fromJson(s, Recipe.class);
    }

}
