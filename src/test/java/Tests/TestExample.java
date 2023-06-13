package Tests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class TestExample {

    @Test
    public void test1() {

        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    public void test2() {

         given().get("https://reqres.in/api/users?page=2")
                .then().statusCode(200)
                .body("data[1].id", equalTo(8));
    }

    @Test
    public void test3() {

        given().get("https://reqres.in/api/users?page=2").then().statusCode(200).log().all();
    }

    @Test
    public void test4() {

         given().get("https://reqres.in/api/users?page=2")
                 .then()
                 .statusCode(200)
                 .log()
                 .all()
                .body("data[1].id", equalTo(8))
                .body("data.first_name", hasItems("Michael", "Lindsay", "Tobias"));
    }

    @Test
    public void test5() {

         given().header("Content-Type", "application/json")
                .get("https://reqres.in/api/users?page=2")
                 .then()
                 .statusCode(200)
                .body("data[1].id", equalTo(8))
                .body("data.first_name", hasItems("Michael", "Lindsay", "Tobias"));
    }

    @Test
    public void test6() {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("name", "Leszek");
        map.put("job", "Developer");

        System.out.println(map);
        JSONObject request = new JSONObject(map);

        System.out.println(request);
        System.out.println(request.toJSONString());

         given().body(request.toJSONString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201);
    }

    @Test
    public void test7() {

        JSONObject request = new JSONObject();

        request.put("name", "Leszek");
        request.put("job", "Developer");

        System.out.println(request);
        System.out.println(request.toJSONString());

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void test8() {

        JSONObject request = new JSONObject();

        request.put("name", "Leszek");
        request.put("job", "Developer");

        System.out.println(request);
        System.out.println(request.toJSONString());

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void test9() {

        when()
                .delete("https://reqres.in/api/users?page=2")
                .then().statusCode(204)
                .log()
                .all();
    }


    //command:
    //npm install -g json-server
    //json-server --watch db.json
    @Test
    public void test10(){

        baseURI = "http://localhost:3000/";

        given().get("/users")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void test11(){

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("firstName", "Ola");
        jsonObject.put("lastName", "Kowalska");
        jsonObject.put("subjectId", "1");


        baseURI = "http://localhost:3000/";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body(jsonObject.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log()
                .all();

    }
    @Test
    public void test13() {

        baseURI = "http://localhost:3000/";

        given().get("/users")
                .then()
                .statusCode(200)
                .log()
                .all()
                .body("firstName", hasItems("Ola"));

//        Response response = RestAssured.get("http://localhost:3000/users");
//        System.out.println(response.getBody().asString());

    }
    @Test
    public void test12() {

        baseURI = "http://localhost:3000/";

         given().param("name","Automation")
                .get("/subjects")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void test14(){

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("lastName", "Nowakowski");

        baseURI = "http://localhost:3000/";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body(jsonObject.toJSONString())
                .when()
                .patch("/users/1")
                .then()
                .statusCode(200)
                .log()
                .all();

    }

    @Test
    public void test15(){

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("firstName", "Piotr");
        jsonObject.put("lastName", "Nowakowski");
        jsonObject.put("subjectId", 9);

        baseURI = "http://localhost:3000/";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body(jsonObject.toJSONString())
                .when()
                .put("/users/1")
                .then()
                .statusCode(200)
                .log()
                .all();

    }

    @Test
    public void test16(){

        baseURI = "http://localhost:3000/";

        when()
                .delete("/users/1")
                .then()
                .statusCode(200);
    }

    @DataProvider(name = "DataPost")
    public Object[][] dataPost(){

        Object[][] object = new Object[2][3];

        object[0][0] = "Adam";
        object[0][1] = "Kowalewski";
        object[0][2] = 1;

        object[1][0] = "Maria";
        object[1][1] = "Konopnicka";
        object[1][2] = 2;

        object[1][0] = "Piotr";
        object[1][1] = "Panda";
        object[1][2] = 3;

        return object;
    }

    @Test(dataProvider = "DataPost")
    public void test17(String firstName, String lastName, int subjectId){

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("firstName", firstName);
        jsonObject.put("lastName", lastName);
        jsonObject.put("subjectId", subjectId);

        baseURI = "http://localhost:3000/";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body(jsonObject.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log()
                .all();

    }

    @DataProvider(name = "DataPost2")
    public Object[][] dataPost2(){

        return new Object[][] {
                {"Adam","Mickiewicz", 3},
                {"Juliusz", "Słowacki", 5},
                {"Leszek", "B", 6},
        };
    }

    @Test(dataProvider = "DataPost2")
    public void test18(String firstName, String lastName, int subjectId){

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("firstName", firstName);
        jsonObject.put("lastName", lastName);
        jsonObject.put("subjectId", subjectId);

        baseURI = "http://localhost:3000/";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body(jsonObject.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log()
                .all();

    }

    @DataProvider(name = "DeleteData")
    public Object[][] deleteData(){
        return new Object[][] {
                {"",1}
        };
    }
    @Test(dataProvider = "DeleteData")
    public void test19(String test, int userId){

        baseURI = "http://localhost:3000/";
        when().delete("/users/"+userId).then().statusCode(200);

    }

}
