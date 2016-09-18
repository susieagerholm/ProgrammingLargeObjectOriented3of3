package hotgammon.network.dtos;

public class RollDiceResponse extends NetworkResponse<int[]> {

	public RollDiceResponse(){
		super.command = "roll-dice";
	}

}
