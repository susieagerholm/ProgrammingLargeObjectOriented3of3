package hotgammon.network.dtos;

public class MoveResponse extends NetworkResponse<Boolean> {

	public MoveResponse(){
		super.command = "move";
	}

}
