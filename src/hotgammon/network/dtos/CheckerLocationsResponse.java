package hotgammon.network.dtos;

public class CheckerLocationsResponse extends NetworkResponse<LocationRepresentation[]> {

	public CheckerLocationsResponse(){
		super.command = "checker-locations";
	}

}