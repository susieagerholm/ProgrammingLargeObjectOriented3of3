package hotgammon.network.dtos;

public class MovesLeftResponse extends NetworkResponse<Integer> {

	public MovesLeftResponse(){
		super.command = "moves-left";
	}

}
