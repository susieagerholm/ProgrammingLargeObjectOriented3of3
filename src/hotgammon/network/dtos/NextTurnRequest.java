package hotgammon.network.dtos;

public class NextTurnRequest extends NetworkRequest {
	public NextTurnRequest(){
		this.command = "next-turn";
	}
}
