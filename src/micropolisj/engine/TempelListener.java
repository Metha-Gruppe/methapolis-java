package micropolisj.engine;

public interface TempelListener {
	public void onCountdown(int count);
	
    void onEnd(boolean wonGame);
}
