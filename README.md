<img width="816" height="970" alt="image" src="https://github.com/user-attachments/assets/bf296ccb-9ec0-4b65-80aa-2a843f0f3b49" /># Security Login/Logout - Auth0 Integration Tutorial


## Prerequisites
This tutorial assumes that you have already completed the section **“Library Project – Reviews On Book Checkout Page”** in *Build a Full Stack App - React and Java Spring Boot* course.

## Overview of Steps
1. Create a developer account on Auth0
1. Create Application and provide Application Information
1. Create API
1. Install Auth0 dependencies
1. Create/ Update `lib/auth0Config.ts`
1. Add Login Status Component
1. Update `App.tsx`
1. Add Logout Functionality
1. Homepage Refactor with Authentication
1. Checkout Book Functionality
1. Spring Boot – Backend Changes
1. Add an Admin Role
1. Run the Application

## 1. Create a Developer Account on Auth0

### SIGN UP
1. Open: [https://developer.auth0.com/](https://developer.auth0.com/)  
2. Sign up.

## 2. Create an Application on Auth0 and provide App information

### Create Application
1. In Auth0 Developer Account, select Applications > Applications > + Create Application ...
1. Give a name: My React App.
1. Choose *Single Page Web Applications*
1. Click: *Create*
1. Click: *Settings*

### Provide App Information
Add Application URIs
- **Allowed Callback URLs**: http://localhost:3000/login/callback
- **Allowed Logout URLs**: http://localhost:3000
- **Allowed Web Origins**: http://localhost:3000
- **Allowed Origins (CORS)**: http://localhost:3000
<img width="2100" height="1288" alt="image" src="https://github.com/user-attachments/assets/ad81f376-4fe7-4aea-b7ee-06b6446de805" />
<img width="1648" height="1352" alt="image" src="https://github.com/user-attachments/assets/41c5018c-3015-495f-931a-ec517db9e129" />
<img width="1548" height="924" alt="image" src="https://github.com/user-attachments/assets/28f889e5-5e27-482f-9421-3ba30fe0d02b" />

Click *Save*

---

## 3. Create API

### Create Application
1. In Auth0 Developer Account, select Applications > API > + Create API...
1. Give a name: My Spring Boot App
1. Identifier: << BACKEND API URI >> Ex: http://localhost:8080
1. Create
<img width="1746" height="360" alt="image" src="https://github.com/user-attachments/assets/eb4e073b-68dc-4e31-9c20-64c3cfca2b3e" />
<img width="816" height="970" alt="image" src="https://github.com/user-attachments/assets/50b2ea52-e9e7-4bba-a6a4-c307494ef86e" />


---

## 4. Install Auth0 Dependencies

**Run the following command** on vs code (React app) console
```
npm install @auth0/auth0-react 
```

## 5. Create/ Update `auth0Config.ts`

File : src/lib/auth0Config.ts

```
export const auth0Config = {
 clientId: '"<<UPDATE-WITH-YOUR-APP-CLIENT-ID>>"',
 issuer: "<<UPDATE-WITH-YOUR-DOMAIN>>",
 audience: "http://localhost:8080",
 redirectUri: window.location.origin+"/callback",
 scope: 'openid profile email'
}
```

Example (lib/auth0Config.ts): 

```
export const auth0Config = {
 clientId: 'abcdefgdE3mAoSMTNAbcdEFgvvLl4ZqU2',
 issuer: '1234567luv2code.us.auth0.com',
 audience: "http://localhost:8080",
 redirectUri: window.location.origin+"/callback",
 scope: 'openid profile email'
}
```

```

## 11. Spring Boot – Backend changes
Spring Boot 3 is required to support Auth0.

### 1. Update pom.xml with Spring Boot 3 dependencies:

File: pom.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.luv2code</groupId>
    <artifactId>spring-boot-library</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>spring-boot-library</name>
    <description>Spring Boot Application for React</description>
    <properties>
        <java.version>17</java.version>
    </properties>
    <dependencies>

        <dependency>
            <groupId>com.okta.spring</groupId>
            <artifactId>okta-spring-boot-starter</artifactId>
            <version>3.0.6</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-rest</artifactId>
        </dependency>

        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```


### 2. Update Application properties with auth0 properties

File: application.properties

```
## issuer url must ends with "/"
okta.oauth2.issuer=https://<< UPDATE-WITH-YOUR-DOMAIN-NAME >/
okta.oauth2.client-id=<< UPDATE-WITH-YOUR-APP-CLIENT-ID >>
okta.oauth2.groupsClaim=https://luv2code-react-library.com/roles
okta.oauth2.audience=http://localhost:8080
```

Example:
```
okta.oauth2.issuer=https://1234567luv2code.us.auth0.com/
okta.oauth2.client-id=abcdefgmAoSMTNAbcdEFgvvLl4ZqU2
okta.oauth2.groupsClaim=https://luv2code-react-library.com/roles
okta.oauth2.audience=http://localhost:8080
```

### 3. Replace javax packages with jakarta
Spring Boot 3 replaces javax package names with jakarta

Search whole project and:
- Replace: `import javax.persistence.*;`
- With: `import jakarta.persistence.*;`

Replace column name with entity property for JPQL

- Replace (in ReviewRepository.java)
`@Query("delete from Review where book\_id in :book\_id")`

- With
`@Query("delete from Review where bookId in :book\_id")`

Replace column name with entity property for JPQL
- Replace(in CheckoutRepository.java)
`@Query("delete from Checkout where book\_id in :book\_id")`
- With
`@Query("delete from Checkout where bookId in :book\_id")`


### 4. Update Security Configuration

File: SecurityConfiguration.java

```
package com.luv2code.springbootlibrary.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Protect endpoints at /api/<type>/secure
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/api/books/secure/**",
                                "/api/reviews/secure/**",
                                "/api/messages/secure/**",
                                "/api/admin/secure/**")
                        .authenticated().anyRequest().permitAll())
                .oauth2Login(withDefaults())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(withDefaults()))
                .cors(withDefaults());

//        Disable Cross Site Request Forgery
        http.csrf(AbstractHttpConfigurer::disable);
        // Add content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());

        // Force a non-empty response body for 401's to make the response friendly
        Okta.configureResourceServer401ResponseBody(http);

        return http.build();
    }

}
```


### 7. Admin user need special permission via roles
(Configuration required on auth0 developer account – see the next step) 


File: AdminController.java

```
package com.luv2code.springbootlibrary.controller;

import com.luv2code.springbootlibrary.requestmodels.AddBookRequest;
import com.luv2code.springbootlibrary.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping("/secure/increase/book/quantity")
    public void increaseBookQuantity(@AuthenticationPrincipal Jwt jwt,
                                     @RequestParam Long bookId) throws Exception {

        List<String> roles = jwt.getClaimAsStringList("https://luv2code-react-library.com/roles");
        String admin = roles != null && !roles.isEmpty() ? roles.get(0) : null;

        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.increaseBookQuantity(bookId);
    }

    @PutMapping("/secure/decrease/book/quantity")
    public void decreaseBookQuantity(@AuthenticationPrincipal Jwt jwt,
                                     @RequestParam Long bookId) throws Exception {
        List<String> roles = jwt.getClaimAsStringList("https://luv2code-react-library.com/roles");
        String admin = roles != null && !roles.isEmpty() ? roles.get(0) : null;

        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.decreaseBookQuantity(bookId);
    }

    @PostMapping("/secure/add/book")
    public void postBook(@AuthenticationPrincipal Jwt jwt,
                         @RequestBody AddBookRequest addBookRequest) throws Exception {
        List<String> roles = jwt.getClaimAsStringList("https://luv2code-react-library.com/roles");
        String admin = roles != null && !roles.isEmpty() ? roles.get(0) : null;

        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.postBook(addBookRequest);
    }

    @DeleteMapping("/secure/delete/book")
    public void deleteBook(@AuthenticationPrincipal Jwt jwt,
                           @RequestParam Long bookId) throws Exception {
        List<String> roles = jwt.getClaimAsStringList("https://luv2code-react-library.com/roles");
        String admin = roles != null && !roles.isEmpty() ? roles.get(0) : null;

        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.deleteBook(bookId);
    }

}

```

### 12. Add an Admin role

#### Add Custom claim post login

* Select: Actions -> Library -> Create Action -> Build from scratch

* Name: custom-claim-post-login

<img width="2574" height="1026" alt="image" src="https://github.com/user-attachments/assets/4559fc9b-92bc-48a2-a2d4-f1dd02dba110" />



* Click on Deploy

Add the following code:

```
exports.onExecutePostLogin = async (event, api) => {
  const namespace = "https://luv2code-react-library.com";
  if (event.authorization) {
    api.idToken.setCustomClaim('email', event.user.email);
    api.idToken.setCustomClaim(`${namespace}/roles`, event.authorization.roles);
    api.accessToken.setCustomClaim(
      `${namespace}/roles`,
      event.authorization.roles
    );
    api.accessToken.setCustomClaim('email', event.user.email);
  }
};
```
<img width="2632" height="1362" alt="image" src="https://github.com/user-attachments/assets/641dd98b-9115-48c7-bdec-403046b966a2" />


<img width="2610" height="1150" alt="image" src="https://github.com/user-attachments/assets/dc54f131-979f-43b0-81bd-027731f9a804" />





Note: namespace can be anything; But, use the same name to fetch roles in frontend and backend

* Frontend:
<img width="2632" height="894" alt="image" src="https://github.com/user-attachments/assets/cd8f316c-0bcb-484f-9b31-a393b3571abc" />


* Backend:
<img width="2604" height="1598" alt="image" src="https://github.com/user-attachments/assets/5bb593d0-6dd1-4766-9bc7-86fa196e4ef4" />



## 13. Run the Application

1. Login
2. Sign Up using Email address or use Social Login
3. Verify Member and Orders
4. Logout


## some caviats 
<img width="1926" height="890" alt="image" src="https://github.com/user-attachments/assets/cc0f7d7a-2e63-4100-8a67-5deb3d6f8de9" />
Dashboard → User Management → Roles → Create role
	• Name: admin
• Dashboard → User Management → Users → choose your test user
Roles tab → Assign Roles → pick admin.
<img width="1907" height="604" alt="image" src="https://github.com/user-attachments/assets/ebf48b0c-6fd2-438d-bf81-e138c323de53" />
<img width="1519" height="563" alt="image" src="https://github.com/user-attachments/assets/63aad99e-68ee-4538-aef1-931a999c42c2" />

Login in Auth0 with ghoust.hunters 
Saifalim14430 is admin as  role.<img width="343" height="68" alt="image" src="https://github.com/user-attachments/assets/ec3162e0-b127-4642-83ff-85cf2f70e5a1" />
<img width="1893" height="472" alt="image" src="https://github.com/user-attachments/assets/ab49452c-376a-4d38-bb69-e29bd62c25cc" />
Actions => triggers => post-login 
<img width="1599" height="833" alt="image" src="https://github.com/user-attachments/assets/9c7ea81a-0dcb-4c13-bfb6-9e1c0ca66b1c" />
<img width="1446" height="749" alt="image" src="https://github.com/user-attachments/assets/31c08a00-199b-4fad-84a2-5c58674837ae" />
<img width="1534" height="773" alt="image" src="https://github.com/user-attachments/assets/2bafeff5-b196-4666-80bc-be5d8063eb15" />
<img width="1521" height="762" alt="image" src="https://github.com/user-attachments/assets/cc606945-604b-4c84-9e1e-0efaa6f8ff70" />


Some more info 


