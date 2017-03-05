package edu.dartmouth.cs.pantryplanner.app.controller;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import edu.dartmouth.cs.pantryplanner.app.R;
import edu.dartmouth.cs.pantryplanner.backend.entity.recipeRecordApi.model.RecipeRecord;
import edu.dartmouth.cs.pantryplanner.common.Recipe;

/**
 * Created by Lucidity on 17/3/3.
 */

public class ExploreListFragment extends Fragment {
    private ArrayList<Fragment> mExploreFragmentList;
    String[] values = new String[]{"Banana Oatmeal Muffin",
            "Shrimp Pesto Pasta",
            "Broccoli Beef",
            "Honey Mustard Chicken and Avacado Salad ",
            "Healthier Chicken Alfredo Pasta",
            "Easy Breakfast Frittata",
            "Chocolate Strawberry Cream Puffs ",
            "Fluffy Japanese Cheesecake "
    };


    public ExploreListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listView_explore_list);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this.getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ExploreListFragment.this.getActivity(), RecipeDetailActivity.class);
                intent.putExtra("RecipeName", (String) adapter.getItem(position));
                startActivity(intent);
            }
        });
        return view;
    }
}