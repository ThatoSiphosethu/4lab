package edu.wctc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Cookbook {

    // Hold all the meals that are read in from the file
    private List<Meal> mealList = new ArrayList<>();

    public void addMeal(String mealTypeStr, String mealNameStr, String caloriesStr) {
        MealType mealType;

        try {
            mealType = MealType.valueOf(mealTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(String.format("Meal Creation Error: Invalid meal type %s. Defaulted to Dinner.", mealTypeStr));
            mealType = MealType.DINNER;
        }

        int calories;

        try {
            calories = Integer.parseInt(caloriesStr);
        } catch (NumberFormatException nfe) {
            System.out.println(String.format("Meal Creation Error: Invalid calories %s. Defaulted to 100.", caloriesStr));
            calories = 100;
        }

        mealList.add(new Meal(mealType, mealNameStr, calories));
    }

    public String doControlBreak() {
        int breakfastCalories = 0;
        int dinnerCalories = 0;
        int lunchCalories = 0;
        int dessertCalories = 0;

        ArrayList<Integer> breakfastCaloriesList = new ArrayList<>();
        ArrayList<Integer> lunchCaloriesList = new ArrayList<>();
        ArrayList<Integer> dinnerCaloriesList = new ArrayList<>();
        ArrayList<Integer> dessertCaloriesList = new ArrayList<>();

        for(Meal meal : mealList){
            if(meal.getMealType().equals(MealType.BREAKFAST)){
                breakfastCalories += meal.getCalories();
                breakfastCaloriesList.add(meal.getCalories());
                continue;
            }
            if(meal.getMealType().equals(MealType.DINNER)){
                dinnerCalories += meal.getCalories();
                dinnerCaloriesList.add(meal.getCalories());
                continue;
            }
            if(meal.getMealType().equals(MealType.LUNCH)){
                lunchCalories += meal.getCalories();
                lunchCaloriesList.add(meal.getCalories());
                continue;
            }
            if(meal.getMealType().equals(MealType.DESSERT)){
                dessertCalories += meal.getCalories();
                dessertCaloriesList.add(meal.getCalories());
            }
            Collections.sort(breakfastCaloriesList);
            Collections.sort(dinnerCaloriesList);
            Collections.sort(lunchCaloriesList);
            Collections.sort(dessertCaloriesList);
        }

        double breakfastMedian = breakfastCaloriesList.size() % 2 == 0 ?
                (double) (breakfastCaloriesList.get(breakfastCaloriesList.size() / 2) +
                        breakfastCaloriesList.get((breakfastCaloriesList.size() / 2) - 1)) / 2 :
                breakfastCaloriesList.get((breakfastCaloriesList.size()) / 2);

        double lunchMedian = lunchCaloriesList.size() % 2 == 0 ?
                (double) (lunchCaloriesList.get(lunchCaloriesList.size() / 2) +
                        lunchCaloriesList.get((lunchCaloriesList.size() / 2) - 1)) / 2 :
                lunchCaloriesList.get((lunchCaloriesList.size()) / 2);

        double dinnerMedian = dinnerCaloriesList.size() % 2 == 0 ?
                (double) (dinnerCaloriesList.get(dinnerCaloriesList.size() / 2) +
                        dinnerCaloriesList.get((dinnerCaloriesList.size() / 2) - 1)) / 2 :
                dinnerCaloriesList.get((dinnerCaloriesList.size()) / 2);

        double dessertMedian = dessertCaloriesList.size() % 2 == 0 ?
                (double) (dessertCaloriesList.get(dessertCaloriesList.size() / 2) +
                        dessertCaloriesList.get((dessertCaloriesList.size() / 2) - 1)) / 2 :
                dessertCaloriesList.get((dessertCaloriesList.size()) / 2);

        return "BREAKFAST = {Total Calories = " + breakfastCalories +
                "\nMean = " + (double) breakfastCalories / breakfastCaloriesList.size() +
                "\nMin = " + breakfastCaloriesList.get(0) +
                "\nMax = " + breakfastCaloriesList.get(breakfastCaloriesList.size() - 1) +
                "\nMedian = " + breakfastMedian + "}" +
                "\n\nLUNCH = {Total Calories = " + lunchCalories +
                "\nMean = " + (double) lunchCalories / lunchCaloriesList.size() +
                "\nMin = " + lunchCaloriesList.get(0) +
                "\nMax = " + lunchCaloriesList.get(lunchCaloriesList.size() - 1) +
                "\nMedian = " + lunchMedian + "}" +
                "\n\nDINNER = {Total Calories = " + dinnerCalories +
                "\nMean = " + (double) dinnerCalories / dinnerCaloriesList.size() +
                "\nMin = " + dinnerCaloriesList.get(0) +
                "\nMax = " + dinnerCaloriesList.get(dinnerCaloriesList.size() - 1) +
                "\nMedian = " + dinnerMedian + "}" +
                "\n\nDESSERT = {Total Calories = " + dessertCalories +
                "\nMean = " + (double) dessertCalories / dessertCaloriesList.size() +
                "\nMin = " + dessertCaloriesList.get(0) +
                "\nMax = " + dessertCaloriesList.get(dessertCaloriesList.size() - 1) +
                "\nMedian = " + dessertMedian + "}";
    }



    public String searchByName(String name) {
        String result = "";
        for (Meal item : mealList) {
            // Uppercase for case insensitivity
            if (item.getItem().contains(name)) {
                result += item + "\n";
            }
        }

        return result.isBlank() ? "No matches found" : result;
    }

    public String searchByType(MealType mealType) {
        String result = "";
        for (Meal item : mealList) {
            if (item.getMealType() == mealType) {
                result += item + "\n";
            }
        }

        return result.isBlank() ? "No matches found" : result;
    }
}
