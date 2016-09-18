package hotgammon.network.dtos;

public abstract class NetworkRequest<T> {
	public String command;
	
	public T arguments;
}
