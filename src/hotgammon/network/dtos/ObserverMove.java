package hotgammon.network.dtos;

public class ObserverMove extends NetworkResponse<MoveRequestArgument> {

	public ObserverMove(){
		this.payload = new MoveRequestArgument();
		super.command = "observer-move";
	}

}
