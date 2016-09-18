package hotgammon.network.dtos;

public class DicesLeftResponse extends NetworkResponse<int[]> {
	public DicesLeftResponse(){
		this.command = "dices-left";
	}
}
