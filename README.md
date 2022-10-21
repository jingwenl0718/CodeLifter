# CodeLifter

## Description 
This is a Java Spring-based application built using Spring Boot, Spring Security, Spring Data JPA, MySQL, JSP, JavaMailSender, and CSS. The project was created in a one-week sprint with another software developer. 

The inspiration behind this project came from the idea that we wanted to create a sense of community for people who are trying to break into software development from non-traditional backgrounds. Therefore, a platform was established where website visitors can share experiences and exchanging ideas via forming/joining study groups, creating/commenting on posts regarding jobs, interview preparation, lifestyle, and success stories. 

During the project, I was responsible for building the data models (including many-to-many and one-to-many) for `jobs`, `interview prep`, `lifestyle` and `success stories` sections, implementing image upload and display functionality, creating custom queries to our database hosted by mySQL and finally deploying the app on AWS. In addition, I was also the project manager designing and organzing agile meetings, overseeing the entire progress, and ensuring that the project gets delivered on time. 

Overall, it was a great development experience! Although we encountered a few challenges along the way, including but not limited to establishing complex data models, collaborating via GitHub, implementing Spring Security for authentication and image upload and display using JPA, this project sharpened my skillsets in terms of problem solving, team colloboration, and project management. 

## Demo


## Table of Contents
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Credits](#credits)
- [License](#license)
- [Todo](#todo)

## Prerequisites
- [Spring Tools Suite](https://spring.io/tools)
- [MySQL](https://downloads.mysql.com/archives/community/)(Mac)
- [MySQL](https://dev.mysql.com/downloads/windows/installer/8.0.html)(Windows)
- [MySQL Workbench] (https://dev.mysql.com/downloads/workbench/#downloads)

## Installation 
1. Clone the repo
   ```
   git clone https://github.com/jingwenl0718/CodeLifter-JavaProject.git
   ```

2. Create a file named "env.properties" in the project root directory
   ```
   touch env.properties
   ```

3. Copy and paste the following code block in the "env.properties" file. (Note: change the fields to match your application.)
   ```
   USER_EMAIL=Your_User_Email
   USER_PASSWORD=Your_User_Password
   DATASOURCE_USERNAME=Your_Datasource_Username
   DATASOURCE_PASSWORD=Your_Datasource_Password
   ```

## Usage
1. Run the application 
   Inside Spring Tool Suite, right click on the project name, and then click on "Run As" -> "Spring Boot App". 

2. Open a browser and navigate to http://localhost:8080.

## Credits
Special thanks to my group mate Evelyn Valles (https://github.com/evelynvalles) for creating an amazing project together! 

## License
MIT License (https://github.com/jingwenl0718/CodeLifter-JavaProject/blob/main/LICENSE)

## Todo
- Allow users to make friends with each other in order to further collaboration.
- Implement private chat between users.   
