package com.videogame.videogameinfo;
import com.videogame.constants.EndPoints;
import com.videogame.model.VideoGamePojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;


public class VideoGameSteps {
  @Step("Creating videogame with id:{0},name:{1},releaseDate:{2},reviewScore:{3},category:{4} and rating:{5}")
  public ValidatableResponse createVideoGame(int id,String name,String releaseDate,int reviewScore,String category,String rating){
    VideoGamePojo videoGamePojo = new VideoGamePojo();
    videoGamePojo.setId( id );
    videoGamePojo.setName( name );
    videoGamePojo.setReleaseDate( releaseDate );
    videoGamePojo.setReviewScore( reviewScore );
    videoGamePojo.setCategory(category);
    videoGamePojo.setRating( rating );

    return SerenityRest.rest()
            .given().log().all()
            .header( "Content-Type","application/json" )
            .body( videoGamePojo ).accept("application/json")
            .when()
            .post(EndPoints.CREATE_NEW_VIDEOGAME)
            .then();
  }
@Step("Getting the videogames information with videpgameId")
  public ValidatableResponse getVideoGameById(int videogameID){
    return SerenityRest.rest()
            .given().log().all()
            .pathParam("id",videogameID)
            .header("Accept","application/json")
            .when()
            .get(EndPoints.GET_SINGLE_VIDEOGAME)
            .then();
}
@Step("Updating videogame information by id:{0},name:{1},releaseDate:{2},reviewScore:{3},category:{4} and rating:{5}")
  public ValidatableResponse updateVideoGame(int id,String name,String releaseDate,int reviewScore,String category,String rating){
    VideoGamePojo videoGamePojo=new VideoGamePojo();
    return SerenityRest.rest().given() .log().all()
            .header("Content-Type","application/json")
            .pathParam("id",id)
            .when()
            .body(videoGamePojo).accept("application/json")
            .put(EndPoints.UPDATE_VIDEOGAME_BY_ID)
            .then();
}
  @Step("Deleting the Services information with VideogameId : {0} ")

  public ValidatableResponse deleteVideoGameById(long videogameId){
    return SerenityRest.rest()
            .given()
            .pathParam("id  =" +
                    "",videogameId)
            .when()
            .delete(EndPoints.DELETE_VIDEOGAME_BY_ID)
            .then();

  }


}
