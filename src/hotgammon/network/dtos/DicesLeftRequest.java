package hotgammon.network.dtos;

public class DicesLeftRequest extends NetworkRequest {
	public DicesLeftRequest(){
		this.command = "dices-left";
	}
}
