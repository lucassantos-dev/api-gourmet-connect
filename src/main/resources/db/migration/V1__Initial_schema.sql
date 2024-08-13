-- V1__Initial_schema.sql

-- Ativar a extensão para UUID
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Criação da tabela Difficulty
CREATE TABLE tb_difficulty (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Criação da tabela Origin
CREATE TABLE tb_origins (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- Criação da tabela IngredientCategory
CREATE TABLE tb_ingredient_category (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Criação da tabela Ingredient
CREATE TABLE tb_ingredient (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    unit VARCHAR(50),
    image_url VARCHAR(255),
    origin_id UUID,
    FOREIGN KEY (origin_id) REFERENCES tb_origins(id)
);

-- Criação da tabela Ingredient_Substitute (tabela de relação)
CREATE TABLE tb_ingredient_substitutes (
    ingredient_id UUID,
    substitute_id UUID,
    PRIMARY KEY (ingredient_id, substitute_id),
    FOREIGN KEY (ingredient_id) REFERENCES tb_ingredient(id),
    FOREIGN KEY (substitute_id) REFERENCES tb_ingredient(id)
);

-- Criação da tabela RecipeCategory
CREATE TABLE tb_recipe_category (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Criação da tabela Recipe
CREATE TABLE tb_recipe (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(255),
    prep_time INT,
    origin_id UUID,
    difficulty_id INT,
    FOREIGN KEY (origin_id) REFERENCES tb_origins(id),
    FOREIGN KEY (difficulty_id) REFERENCES tb_difficulty(id)
);

-- Criação da tabela Recipe_Ingredients (tabela de relação)
CREATE TABLE tb_recipe_ingredient (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    recipe_id UUID,
    ingredient_id UUID,
    quantity DECIMAL(10, 2),
    FOREIGN KEY (recipe_id) REFERENCES tb_recipe(id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES tb_ingredient(id) ON DELETE CASCADE
);

-- Criação da tabela Recipe_Categories (tabela de relação)
CREATE TABLE tb_recipe_categories (
    recipe_id UUID,
    category_id UUID,
    PRIMARY KEY (recipe_id, category_id),
    FOREIGN KEY (recipe_id) REFERENCES tb_recipe(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES tb_recipe_category(id) ON DELETE CASCADE
);

-- Criação da tabela Recipe_Instructions (tabela para armazenar instruções como uma coleção)
CREATE TABLE recipe_instructions (
    recipe_id UUID,
    instruction TEXT,
    FOREIGN KEY (recipe_id) REFERENCES tb_recipe(id) ON DELETE CASCADE
);
