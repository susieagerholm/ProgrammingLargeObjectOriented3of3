package hotgammon.network.dtos;

public class MovesLeft extends NetworkRequest {

	public MovesLeft(){
		super.command = "moves-left";
	}

}
