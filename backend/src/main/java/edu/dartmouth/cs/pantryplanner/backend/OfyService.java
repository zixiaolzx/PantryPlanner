package edu.dartmouth.cs.pantryplanner.backend;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

import edu.dartmouth.cs.pantryplanner.backend.entity.*;

/**
 * Objectify service wrapper so we can statically register our persistence classes
 * More on Objectify here : https://code.google.com/p/objectify-appengine/
 */
public class OfyService {

    static {
        ObjectifyService.register(RegistrationRecord.class);
        ObjectifyService.register(UserRecord.class);
        ObjectifyService.register(MealPlanRecord.class);
        ObjectifyService.register(RecipeRecord.class);
        ObjectifyService.register(ShoppingListRecord.class);
        ObjectifyService.register(HistoryRecord.class);
        ObjectifyService.register(PantryRecord.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
