/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

/**
 * Builder模式创建对象
 * 
 * @since 1.8
 * @author Mingle
 */
public class BuilderPattern {
    public static void main(String[] args) {
        NutritionFactsThree nf = new NutritionFactsThree.BuilderInner(240, 8)
        .calories(100).sodium(35).carbohydrate(27).build();
        System.out.println(nf);
    }
}

/**
 * 重叠构造器模式(Telescoping constructor pattern)
 * 当有很多参数时,客户端代码难以编写
 */
class NutritionFactsOne {
    @SuppressWarnings("unused")
    private final int servingSize;    // (mL)                required
    @SuppressWarnings("unused")
    private final int servings;        // (per container)    required
    @SuppressWarnings("unused")
    private final int calories;        //                    optional
    @SuppressWarnings("unused")
    private final int fat;            // (g)                optional
    @SuppressWarnings("unused")
    private final int sodium;        // (mg)                optional
    @SuppressWarnings("unused")
    private final int carbohydrate;    // (g)                optional
    
    public NutritionFactsOne(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }
    
    public NutritionFactsOne(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }
    
    public NutritionFactsOne(int servingSize, int servings, int calories,
            int fat) {
        this(servingSize, servings, calories, fat, 0);
    }
    
    public NutritionFactsOne(int servingSize, int servings, int calories,
            int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }
    
    public NutritionFactsOne(int servingSize, int servings, int calories,
            int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
    
}

/**
 * JavaBeans模式,并发时可能处于不一致状态
 */
class NutritionFactsTwo {
    @SuppressWarnings("unused")
    private int servingSize = -1;
    @SuppressWarnings("unused")
    private int servings = -1;
    @SuppressWarnings("unused")
    private int calories = 0;
    @SuppressWarnings("unused")
    private int fat = 0;
    @SuppressWarnings("unused")
    private int sodium = 0;
    @SuppressWarnings("unused")
    private int carbohydrate = 0;
    
    public NutritionFactsTwo() {}

    // Setters
    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }
    
}

/**
 * Builder模式,比较好的方式
 */
class NutritionFactsThree {
    @SuppressWarnings("unused")
    private final int servingSize;
    @SuppressWarnings("unused")
    private final int servings;
    @SuppressWarnings("unused")
    private final int calories;
    @SuppressWarnings("unused")
    private final int fat;
    @SuppressWarnings("unused")
    private final int sodium;
    @SuppressWarnings("unused")
    private final int carbohydrate;
    
    interface Builder<T> {
        T build();
    }
    
    public static class BuilderInner implements Builder<NutritionFactsThree> {
        // Required paarmeters
        private final int servingSize;
        private final int servings;
        
        // Optional parameters - initialized to default value
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;
        
        public BuilderInner(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }
        
        public BuilderInner calories(int val) {
            calories = val;
            return this;
        }
        
        public BuilderInner fat(int val) {
            fat = val;
            return this;
        }
        
        public BuilderInner sodium(int val) {
            sodium = val;
            return this;
        }
        
        public BuilderInner carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }
        
        public NutritionFactsThree build() {
            return new NutritionFactsThree(this);
        }
    }
    
    private NutritionFactsThree(BuilderInner builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
    
}
