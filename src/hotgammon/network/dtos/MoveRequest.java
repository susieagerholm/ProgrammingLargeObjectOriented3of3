package hotgammon.network.dtos;

import hotgammon.common.Location;

public class MoveRequest extends NetworkRequest<MoveRequestArgument> {

	public MoveRequest(Location from, Location to){
		this.command = "move";
		this.arguments = new MoveRequestArgument();
		this.arguments.to = to;
		this.arguments.from = from;
	}
	
	/**
	 * Parameterless constructor used for json serialization
	 */
	public MoveRequest(){
		this.command = "move";
		this.arguments = new MoveRequestArgument();
	}
	
}