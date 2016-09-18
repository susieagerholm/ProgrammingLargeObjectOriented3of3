package hotgammon.network.dtos;

public class RollDiceRequest extends NetworkRequest {

	public RollDiceRequest(){
		super.command = "roll-dice";
	}


}
