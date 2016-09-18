package hotgammon.network.dtos;

public abstract class NetworkResponse<T> {
	public String command;
	
	public T payload;
}
