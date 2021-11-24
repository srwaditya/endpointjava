public class GetMethods {

    public ReadPropertyFile prop;

    public GetMethods() {
        prop = new ReadPropertyFile();
    }

    @Test
    public void testGetAllBookingIDStatus() throws IOException {
        given()
                .contentType("application/json")
                .when().get(prop.getKey("HOST") + "/booking")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetInvalidBookingEndpoint() throws IOException {
        given()
                .contentType("application/json")
                .when().get(prop.getKey("HOST") + "/bookings")
                .then()
                .statusCode(404);
    }

    @Test
    public void testGetAllBookingIDBody() throws IOException {
        given()
                .contentType("application/json")
                .when().get(prop.getKey("HOST") + "/booking")
                .then()
                .body(containsString("bookingid"));
    }

    @Test
    public void testLatency() throws Throwable {
		Response response = given()
				.contentType("application/json")
				.when()
				.get(prop.getKey("HOST") + "/booking");
        long latency = response.getTime();
        if (latency > 2000) {
            throw new Exception(
                    "Response Time Failed: Should be Less than 2000L;" + "Actual Response Time: " + latency);
        }
    }
}