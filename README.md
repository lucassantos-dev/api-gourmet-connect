# API Gourmet Connect

API Gourmet Connect is a RESTful API built with Spring Boot that provides functionality for managing gourmet recipes and ingredients.

## Features

- Manage recipes: create, read, update, and delete recipes.
- Manage ingredients: create, read, update, and delete ingredients.
- User authentication and authorization.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database (for development)
- Maven

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/lucassantos-dev/api-gourmet-connect.git
    cd api-gourmet-connect
    ```

2. Build the project with Maven:
    ```bash
    mvn clean install
    ```

3. Run the application:
    ```bash
    mvn spring-boot:run
    ```

4. The application will start and be accessible at `http://localhost:8080`.

## API Endpoints

### Recipes

- `GET /recipes` - Get all recipes
- `GET /recipes/{id}` - Get a recipe by ID
- `POST /recipes` - Create a new recipe
- `PUT /recipes/{id}` - Update a recipe
- `DELETE /recipes/{id}` - Delete a recipe

### Ingredients

- `GET /ingredients` - Get all ingredients
- `GET /ingredients/{id}` - Get an ingredient by ID
- `POST /ingredients` - Create a new ingredient
- `PUT /ingredients/{id}` - Update an ingredient
- `DELETE /ingredients/{id}` - Delete an ingredient

## Contributing

1. Fork the repository.
2. Create a new branch.
3. Make your changes.
4. Submit a pull request.

## License

This project is licensed under the MIT License.

## Contact

Lucas Santos - [GitHub](https://github.com/lucassantos-dev)

---

Feel free to contribute to the project by opening issues or submitting pull requests.

