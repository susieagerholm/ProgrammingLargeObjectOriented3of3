package hotgammon.network.dtos;

public class ObserverRollDice extends NetworkResponse<int[]> {

	public ObserverRollDice(){
		super.command = "observer-roll-dice";
	}

}
