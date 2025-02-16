<!-- ABOUT THE PROJECT -->
# ABOUT THE PROJECT

	This is the backend of a Food Delivery App.
    I practiced working with Hibernate, JPA Repository and RestController.
    The app has a database with 7 tables.
    I used the strategy InheritanceType.TABLE_PER_CLASS for the inheritance between the classes.
    The app has 2 types of users: Customer and DeliveryPartner.
    The customer can see the menus of each restaurant and place an order. 
    The available delivery partner will take its order and deliver it.
    
    I implemented 81% line coverage in unit tests. 
    I used Aspect Oriented Programming (AOP) and log4j for logging.
    I used Postman for testing the API and MySQL Workbench for the database.
    I used the MVC design pattern for the architecture of the app.
    I handled the exceptions with Custom Exception Handling.
    I used Spring Expression Language (SpEL).

    The postman collection is in the root folder of this repo.

<!-- Database Architecture -->
# Database Architecture
![alt text](assets/db_diagram.png)

### Built With
* [![Spring][Spring]][Spring-url]
* [![Java][Java]][Java-url]
* [![MySQL][MySQL]][MySQL-url]
* [![Postman][Postman]][Postman-url]

<!-- GETTING STARTED -->
## Getting Started

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/CosminAvramescu/FoodDeliveryApp.git
   ```

2. Install Java, MySQL, MySQL Workbench, Maven and Postman

3. Create your database schema in MySQL Workbench and change the variables in application.properties accordingly

<!-- USAGE EXAMPLES -->
## Usage

1. Open the MySQL Workbench
2. Run Spring backend
3. Run the requests from the Postman collection

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.



<!-- CONTACT -->
## Contact

Cosmin-Alexandru Avramescu - [@my_linkedin](https://www.linkedin.com/in/cosmin-avramescu/)

Project Link: [https://github.com/CosminAvramescu/FoodDeliveryApp](https://github.com/CosminAvramescu/FoodDeliveryApp)


<!-- ACKNOWLEDGMENTS -->
## Acknowledgments
* [Spring Documentation](https://docs.spring.io/spring-boot/docs/current)
* [AOP programming](https://www.baeldung.com/spring-aspect-oriented-programming-logging)
* [Inheritance Strategy](https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/)


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/screenshot.png
[Spring]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
[Java]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://docs.oracle.com/en/java/
[MySQL]: https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white
[MySQL-url]: https://www.mysql.com/
[Postman]: https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white
[Postman-url]: https://www.postman.com/