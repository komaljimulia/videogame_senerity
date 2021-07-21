package com.videogame.videogameinfo;

import com.videogame.testbase.TestBase;
import com.videogame.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class VideoGameCURDTestWithSteps extends TestBase {
    static int id = 1 + TestUtils.getRandomValueInt();
    static String name = "Super Mario" ;
    static String releaseDate = "2021-06-15T09:14:42.993Z" ;
    static int reviewScore = 678;
    static String category = "Kids" ;
    static String rating = "PG-13" ;

    @Steps
    VideoGameSteps videoGameSteps;

    @Title("This will create new videogame")
    @Test
    public void test001() {
        videoGameSteps.createVideoGame(id, name, releaseDate, reviewScore, category, rating).log().all()
                .statusCode(200)
                .extract().response()
                .body().jsonPath();

    }
    @Title("This will get videogame by id")
    @Test
    public void test002(){
        videoGameSteps.getVideoGameById(id).statusCode(200);

    }
    @Title("This will update videogame by id")
    @Test
    public void test003(){
        name=name+"_new";
        category=category+"_changed";
        videoGameSteps.updateVideoGame(id, name, releaseDate, reviewScore, category, rating).statusCode(200);
       // videoGameSteps.getVideoGameById(id).body("name",equalTo(name));
    }
    @Title("This will delete videogame information by id")
    @Test
    public void test004(){
        videoGameSteps.deleteVideoGameById(id).statusCode(200);
        videoGameSteps.getVideoGameById(id).statusCode(500);

    }

}