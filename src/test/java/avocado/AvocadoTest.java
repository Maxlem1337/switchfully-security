package avocado;

import com.cegeka.switchfully.security.ArmyInfoDto;
import com.cegeka.switchfully.security.ArmyResource;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class AvocadoTest extends RestAssuredTest {

    @Test
    public void getDeployedArmyInfo_givenKnownUsernameAndPasswordEncodedAsBasicAuthenticationHeader_thenShouldAllowAccess() {
        ArmyInfoDto actual = givenRequestForUser("JMILLER", "THANKS")
                .when()
                .get(String.format("%s/%s", ArmyResource.ARMY_RESOURCE_PATH, "Belgium"))
                .then()
                .assertThat()
                .statusCode(OK.value())
                .extract()
                .body()
                .as(ArmyInfoDto.class);

        assertThat(actual.country).isEqualTo("Belgium");
    }

    @Test
    public void getDeployedArmyInfo_givenKnownUsernameAndWrongPasswordEncodedAsBasicAuthenticationHeader_thenShouldNotAllowAccess() {
        givenRequestForUser("JMILLER", "JBAKER")
                .when()
                .get(String.format("%s/%s", ArmyResource.ARMY_RESOURCE_PATH, "Belgium"))
                .then()
                .assertThat()
                .statusCode(UNAUTHORIZED.value());
    }

    @Test
    public void getDeployedArmyInfo_givenUnknownUsernameAndPasswordEncodedAsBasicAuthenticationHeader_thenShouldNotAllowAccess() {
        givenRequestForUser("FONZ", "AYE")
                .when()
                .get(String.format("%s/%s", ArmyResource.ARMY_RESOURCE_PATH, "Belgium"))
                .then()
                .assertThat()
                .statusCode(UNAUTHORIZED.value());
    }

    @Test
    public void launchNukes_givenUnknownUsernameAndPasswordEncodedAsBasicAuthenticationHeader_thenShouldNotAllowAccess() {
        givenRequestForUser("FONZ", "AYE")
                .when()
                .get(ArmyResource.ARMY_NUKE_PATH)
                .then()
                .assertThat()
                .statusCode(UNAUTHORIZED.value());
    }

    @Test
    public void launchNukes_givenKnownUsernameAndPasswordEncodedAsBasicAuthenticationHeader_thenShouldAllowAccess() {
        givenRequestForUser("JMILLER", "THANKS")
                .when()
                .get(ArmyResource.ARMY_NUKE_PATH)
                .then()
                .assertThat()
                .statusCode(OK.value());
    }

    @Test
    public void launchNukes_givenKnownUsernameAndWrongPasswordEncodedAsBasicAuthenticationHeader_thenShouldNotAllowAccess() {
        givenRequestForUser("JMILLER", "JBAKER")
                .when()
                .get(ArmyResource.ARMY_NUKE_PATH)
                .then()
                .assertThat()
                .statusCode(UNAUTHORIZED.value());
    }

    @Test
    public void joinArmy_givenUnknownUsernameAndPasswordEncodedAsBasicAuthenticationHeader_thenShouldNotAllowAccess() {
        givenRequestForUser("FONZ", "AYE")
                .when()
                .post(ArmyResource.ARMY_JOIN_PATH)
                .then()
                .assertThat()
                .statusCode(UNAUTHORIZED.value());
    }

    @Test
    public void joinArmy_givenKnownUsernameAndPasswordEncodedAsBasicAuthenticationHeader_thenShouldAllowAccess() {
        givenRequestForUser("JMILLER", "THANKS")
                .when()
                .post(ArmyResource.ARMY_JOIN_PATH)
                .then()
                .assertThat()
                .statusCode(OK.value());
    }

    @Test
    public void joinArmy_givenKnownUsernameAndWrongPasswordEncodedAsBasicAuthenticationHeader_thenShouldNotAllowAccess() {
        givenRequestForUser("JMILLER", "JBAKER")
                .when()
                .post(ArmyResource.ARMY_JOIN_PATH)
                .then()
                .assertThat()
                .statusCode(UNAUTHORIZED.value());
    }

    @Test
    public void promotePrivate_givenUnknownUsernameAndPasswordEncodedAsBasicAuthenticationHeader_thenShouldNotAllowAccess() {
        givenRequestForUser("FONZ", "AYE")
                .when()
                .post(ArmyResource.ARMY_PROMOTE_PRIVATE_PATH + "/FONZ")
                .then()
                .assertThat()
                .statusCode(UNAUTHORIZED.value());
    }

    @Test
    public void promotePrivate_givenKnownUsernameAndPasswordEncodedAsBasicAuthenticationHeader_thenShouldAllowAccess() {
        givenRequestForUser("JMILLER", "THANKS")
                .when()
                .post(ArmyResource.ARMY_PROMOTE_PRIVATE_PATH + "/FONZ")
                .then()
                .assertThat()
                .statusCode(OK.value());
    }

    @Test
    public void promotePrivate_givenKnownUsernameAndWrongPasswordEncodedAsBasicAuthenticationHeader_thenShouldNotAllowAccess() {
        givenRequestForUser("JMILLER", "JBAKER")
                .when()
                .post(ArmyResource.ARMY_PROMOTE_PRIVATE_PATH + "/FONZ")
                .then()
                .assertThat()
                .statusCode(UNAUTHORIZED.value());
    }

    @Test
    public void dischargeSoldier_givenUnknownUsernameAndPasswordEncodedAsBasicAuthenticationHeader_thenShouldNotAllowAccess() {
        givenRequestForUser("FONZ", "AYE")
                .when()
                .post(ArmyResource.ARMY_DISCHARGE_SOLDIER_PATH + "/FONZ")
                .then()
                .assertThat()
                .statusCode(UNAUTHORIZED.value());
    }

    @Test
    public void dischargeSoldier_givenKnownUsernameAndPasswordEncodedAsBasicAuthenticationHeader_thenShouldAllowAccess() {
        givenRequestForUser("JMILLER", "THANKS")
                .when()
                .post(ArmyResource.ARMY_DISCHARGE_SOLDIER_PATH + "/FONZ")
                .then()
                .assertThat()
                .statusCode(OK.value());
    }

    @Test
    public void dischargeSoldier_givenKnownUsernameAndWrongPasswordEncodedAsBasicAuthenticationHeader_thenShouldNotAllowAccess() {
        givenRequestForUser("JMILLER", "JBAKER")
                .when()
                .post(ArmyResource.ARMY_DISCHARGE_SOLDIER_PATH + "/FONZ")
                .then()
                .assertThat()
                .statusCode(UNAUTHORIZED.value());
    }
}
